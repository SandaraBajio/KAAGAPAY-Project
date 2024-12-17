package ui;

import managers.*;
import db.*;
import entities.*;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {

    private MissionDatabase missionDatabase;
    private MissionManager missionManager;
    private UserDatabase userDatabase;
    private UserManager userManager;
    private DonationManager donationManager;
    private DonationDatabase donationDatabase;
    private Scanner scanner;
    private ConsoleDesign consoleDesign;

    public Menu(MissionDatabase missionDatabase, MissionManager missionManager, UserDatabase userDatabase, DonationManager donationManager, DonationDatabase donationDatabase) {
        this.missionDatabase = missionDatabase;
        this.missionManager = missionManager;
        this.userDatabase = userDatabase;
        this.userManager = new UserManager(userDatabase);
        this.donationManager = donationManager;
        this.donationDatabase = donationDatabase;
        this.scanner = new Scanner(System.in);
        this.consoleDesign = new ConsoleDesignImpl();
    }

    public void displayMainMenu() {
        consoleDesign.clearScreen();
        consoleDesign.printLogo();
        boolean exit = false;

        consoleDesign.setColor("yellow");
            System.out.println("             Together, We Rebuild Stronger.");

        while (!exit) {
            consoleDesign.setColor("magenta");
            System.out.println("\nMain Menu:");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In as User");
            System.out.println("3. Log In as Admin");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        handleSignUp();
                        break;
                    case 2:
                        handleUserLogin();
                        break;
                    case 3:
                        handleAdminLogin();
                        break;
                    case 4:
                        exit = true;
                        consoleDesign.blinkText("Thank you for using the system. Goodbye!", 3);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.nextLine(); 
            }
        }
    }

    private void handleSignUp() {
        consoleDesign.clearScreen();
        consoleDesign.printLogo();
        consoleDesign.setColor("magenta");
        System.out.println("\n--- Sign Up ---");
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        try {
            userManager.handleSignUp(username, password);
        } catch (Exception e) {
            System.out.println("An error occurred during sign-up. Please try again.");
        }
    }

    private void handleUserLogin() {
        consoleDesign.setColor("magenta");
        System.out.println("\n--- Log In as User ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            if (userManager.handleUserLogin(username, password)) {
                System.out.println("User login successful! Welcome, " + username + ".");
                handleUserMenu(username); 
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login. Please try again.");
        }
    }

    private void handleAdminLogin() {
        consoleDesign.setColor("magenta");
        System.out.println("\n--- Log In as Admin ---");
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        try {
            if (userManager.handleAdminLogin(password)) {
                System.out.println("Admin login successful!");
                handleAdminMenu();
            } else {
                System.out.println("Invalid admin password. Access denied.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during admin login. Please try again.");
        }
    }

    private void handleAdminMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            consoleDesign.setColor("magenta");
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Manage Missions");
            System.out.println("2. Manage Volunteers");
            System.out.println("3. Manage Donations");
            System.out.println("4. Go Back");
            System.out.print("Enter your choice: ");
            int choice = 0;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        manageMissions();
                        break;
                    case 2:
                        manageVolunteers();
                        break;
                    case 3:
                        manageDonations();
                        break;
                    case 4:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.nextLine(); 
            }
        }
    }

    private void manageMissions() {
        consoleDesign.clearScreen();
        consoleDesign.printLogo();
        consoleDesign.setColor("magenta");
        System.out.println("\n--- Manage Missions ---");
        System.out.println("1. Add Mission");
        System.out.println("2. Delete Mission");
        System.out.println("3. Update Mission Status");
        System.out.println("4. View Missions");
        System.out.println("5. Go Back");
        System.out.print("Enter your choice: ");
        int missionChoice = 0;

        try {
            missionChoice = scanner.nextInt();
            scanner.nextLine();

            switch (missionChoice) {
                case 1:
                    missionManager.addMission();
                    break;
                case 2:
                    System.out.print("Enter the name of the mission to delete: ");
                    String missionToDelete = scanner.nextLine();
                    missionManager.deleteMission(missionToDelete);
                    break;
                case 3:
                    System.out.print("Enter the name of the mission to update status: ");
                    String missionToUpdate = scanner.nextLine();
                    System.out.print("Update status to (ongoing/completed): ");
                    String updateStatus = scanner.nextLine();
                    missionManager.updateMissionStatus(missionToUpdate, updateStatus);
                    break;
                case 4:
                    missionManager.viewAllMission();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid option.");
            scanner.nextLine();
        }
    }

    private void manageVolunteers() {
        consoleDesign.setColor("magenta");
        consoleDesign.clearScreen();
        consoleDesign.printLogo();
        System.out.println("\n--- Manage Volunteers ---");
        System.out.println("1. Approve Volunteer Mission");
        System.out.println("2. Show Volunteer Profile");
        System.out.println("3. Go Back");
        System.out.print("Enter your choice: ");
        int choice = 0;

        try {
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Volunteer Username to approve for mission: ");
                    String username = scanner.nextLine();
                    Volunteer volunteer = userDatabase.getVolunteerByUsername(username);
                    if (volunteer != null) {
                        approveVolunteerForMission(volunteer);
                    } else {
                        System.out.println("Volunteer not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Volunteer Username to view profile: ");
                    username = scanner.nextLine();
                    volunteer = userDatabase.getVolunteerByUsername(username);
                    if (volunteer != null) {
                        showVolunteerProfile(volunteer);
                    } else {
                        System.out.println("Volunteer not found.");
                    }
                    break;

                case 3:
                    System.out.println("Returning to previous menu...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    manageVolunteers();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid option.");
            scanner.nextLine();
        }
    }

    private void approveVolunteerForMission(Volunteer volunteer) {
        if (!volunteer.isApprovedForMission()) {
            volunteer.setApprovedForMission(true);
            userDatabase.updateVolunteer(volunteer);
            System.out.println("Volunteer " + volunteer.getUsername() + " has been approved for the mission.");
        } else {
            System.out.println("Volunteer " + volunteer.getUsername() + " is already approved for the mission.");
        }
    }

    private void showVolunteerProfile(Volunteer volunteer) {
        System.out.println("\n--- Volunteer Profile ---");
        System.out.println("Username: " + volunteer.getUsername());
        System.out.println("Approved for Mission: " + (volunteer.isApprovedForMission() ? "Yes" : "No"));
    }

    private void manageDonations() {
        boolean backToDonationsMenu = false;
        while (!backToDonationsMenu) {
            consoleDesign.setColor("magenta");
            System.out.println("\n--- Manage Donations ---");
            System.out.println("1. View Total Donations");
            System.out.println("2. Withdraw Donation with Purpose");
            System.out.println("3. Go Back");
            System.out.print("Enter your choice: ");
            int choice = 0;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewTotalDonations();
                        break;
                    case 2:
                        withdrawDonation();
                        break;
                    case 3:
                        backToDonationsMenu = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();
            }
        }
    }

    public void viewTotalDonations() {
        try {
            double totalDonations = donationManager.getTotalMonetaryDonations();
            System.out.println("Total Monetary Donations: Php " + totalDonations);
        } catch (Exception e) {
            System.out.println("Error retrieving donation data.");
        }
    }

    public void withdrawDonation() {
        consoleDesign.setColor("magenta");
        System.out.print("Enter the purpose for the withdrawal: ");
        String purpose = scanner.nextLine();

        System.out.print("Enter the amount to withdraw: Php ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            boolean success = donationDatabase.withdrawDonation(amount);
            if (success) {
                System.out.println("Withdrawal of Php " + amount + " for the purpose of '" + purpose + "' completed.");
                
                
            } else {
                System.out.println("Insufficient funds for this withdrawal.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the withdrawal. Please try again.");
        }
    }

    private void handleUserMenu(String username) {
        boolean backToMain = false;

        while (!backToMain) {
            consoleDesign.setColor("magenta");
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Be a Volunteer");
            System.out.println("2. Make a Donation");
            System.out.println("3. Go Back");
            System.out.print("Enter your choice: ");
            int choice = 0;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        becomeVolunteer(username);
                        break;
                    case 2:
                        handleDonation(username);
                        break;
                    case 3:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine();
            }
        }
    }

    private void becomeVolunteer(String username) {
        System.out.println("\n--- Become a Volunteer ---");
    
        if (userDatabase.getVolunteerByUsername(username) != null) {
            System.out.println("You are already a volunteer.");
            return;
        }
    
        System.out.println("\nAvailable Missions:");
        missionManager.viewAllMission(); 
    
        System.out.print("Please choose a mission by entering its ID: ");
        int missionId = Integer.parseInt(scanner.nextLine());
    
        Mission mission = missionDatabase.getMissionById(missionId);
    
        if (mission == null) {
            System.out.println("Mission not found.");
            return;
        }
    
        Volunteer volunteer = new Volunteer(username, mission.getMissionName());
        volunteer.setApprovedForMission(false);
    
        if (userDatabase.addVolunteerInfo(username, volunteer)) {
            System.out.println("You have successfully registered as a volunteer for the mission: " + mission.getMissionName());
            System.out.println("Your volunteering status is pending until approval by the admin.");
        } else {
            System.out.println("There was an issue registering you as a volunteer.");
        }
    }

    private void handleDonation(String username) {
        consoleDesign.clearScreen();
        consoleDesign.printLogo();
        consoleDesign.setColor("magenta");
        System.out.println("\n--- Make a Donation ---");
    
        System.out.print("Please enter the amount you want to donate: ");
        double donationAmount = 0;
        boolean validAmount = false;
    
        while (!validAmount) {
            try {
                donationAmount = scanner.nextDouble();
                scanner.nextLine();
    
                if (donationAmount <= 0) {
                    System.out.println("Donation amount must be greater than zero. Please try again.");
                } else {
                    validAmount = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid donation amount.");
                scanner.nextLine();
            }
        }
    
        Donation donation = new Donation(username, donationAmount);

        if (donationDatabase.addDonation(donation)) {
            System.out.println("Thank you for your donation of Php " + donationAmount + "!");
            donationManager.addMonetaryDonation(username, donationAmount);
        } else {
            System.out.println("There was an issue processing your donation. Please try again later.");
        }
    }
    
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
