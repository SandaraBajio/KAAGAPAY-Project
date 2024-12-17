package db;

import java.util.HashMap;
import entities.Volunteer;

public class UserDatabase {
    private HashMap<String, String> users; 
    private HashMap<String, String> admins;
    private HashMap<String, Volunteer> volunteerInfo;

    public UserDatabase() {
        users = new HashMap<>();
        admins = new HashMap<>();
        volunteerInfo = new HashMap<>();

        admins.put("admin", "admin123"); 
    }

    // User-related methods
    public boolean addUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; 
        }
        users.put(username, password);
        return true;
    }

    public boolean loginUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public boolean loginAdmin(String password) {
        return admins.containsKey("admin") && admins.get("admin").equals(password);
    }

    public boolean addVolunteerInfo(String username, Volunteer volunteer) {
        if (volunteerInfo.containsKey(username)) {
            return false; 
        }
        volunteerInfo.put(username, volunteer);
        return true;
    }

    public Volunteer getVolunteerByUsername(String username) {
        return volunteerInfo.get(username); 
    }

    public boolean updateVolunteer(Volunteer updatedVolunteer) {
        String username = updatedVolunteer.getUsername();
        if (volunteerInfo.containsKey(username)) {
            volunteerInfo.put(username, updatedVolunteer);
            return true;
        }
        return false;
    }

    public boolean isUserRegistered(String username) {
        return users.containsKey(username);
    }

    public boolean isAdminRegistered(String username) {
        return admins.containsKey(username);
    }

    public HashMap<String, Volunteer> getAllVolunteerInfo() {
        return new HashMap<>(volunteerInfo); 
    }
}
