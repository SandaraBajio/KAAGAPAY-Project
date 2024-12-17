import managers.*;
import db.*;
import ui.Menu;

public class KaagapayMain {
    public static void main(String[] args) {
        MissionDatabase missionDatabase = new MissionDatabase(); 
        MissionManager missionManager = new MissionManager(missionDatabase);
        UserDatabase userDatabase = new UserDatabase();
        DonationManager donationManager = new DonationManager();
        DonationDatabase donationDatabase = new DonationDatabase();

        Menu menu = new Menu(missionDatabase, missionManager, userDatabase, donationManager, donationDatabase);
        menu.displayMainMenu();
    }
}
 