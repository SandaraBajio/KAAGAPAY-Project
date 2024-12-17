package managers;

import db.UserDatabase;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private UserDatabase userDatabase;
    private Map<String, String> userStatuses;

    public UserManager(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
        this.userStatuses = new HashMap<>();
    }

    public void handleSignUp(String username, String password) {
        if (userDatabase.addUser(username, password)) {
            System.out.println("Sign-up successful! You can now log in.");
        } else {
            System.out.println("Username already exists. Please try a different username.");
        }
    }

    public boolean handleUserLogin(String username, String password) {
        return userDatabase.loginUser(username, password);
    }

    public boolean handleAdminLogin(String password) {
        return userDatabase.loginAdmin(password);
    }

    public boolean updateUserStatus(String username, String status) {
        if (username == null || status == null) {
            return false;
        }
        userStatuses.put(username, status);
        System.out.println("Status for " + username + " updated to " + status + ".");
        return true;
    }

    public String getUserStatus(String username) {
        return userStatuses.getOrDefault(username, "Unknown");
    }
}
