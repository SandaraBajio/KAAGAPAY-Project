package managers;

import entities.Donation;
import db.DonationDatabase;
import java.util.List;

public class DonationManager {
    private DonationDatabase donationDatabase;
    private double totalMonetaryDonations;
    private String donationGoal;
    private double goalProgress;

    public DonationManager() {
        donationDatabase = new DonationDatabase();
        totalMonetaryDonations = 0;
        goalProgress = 0;
    }

    public void addMonetaryDonation(String donorName, double amount) {
        Donation donation = new Donation(donorName, amount);
        donationDatabase.addDonation(donation);
        totalMonetaryDonations += amount;
    }

    public void addInKindDonation(String donorName, Donation.DonationCategory category, double quantity, String description) {
        Donation donation = new Donation(donorName, category, quantity, description);
        donationDatabase.addDonation(donation);
    }

    public void setDonationGoal(String goalDescription) {
        this.donationGoal = goalDescription;
        this.goalProgress = 0;
    }

    public void updateGoalProgress(double progress) {
        this.goalProgress += progress;
    }

    public double getTotalMonetaryDonations() {
        return totalMonetaryDonations;
    }

    public List<Donation> getDonationsByDonor(String donorName) {
        return donationDatabase.getDonationsByDonor(donorName);
    }

    public List<Donation> getAllDonations() {
        return donationDatabase.getAllDonations();
    }

    public String getDonationGoal() {
        return donationGoal + " Progress: " + goalProgress;
    }

    public void displayAllDonations() {
        for (Donation donation : donationDatabase.getAllDonations()) {
            System.out.println(donation);
        }
    }

    public void processMoneyDonation(double amount) {
        System.out.println("Processing money donation of: $" + amount);
    }

    public void processGoodsDonation(String goodsType, int quantity) {
        System.out.println("Processing donation of " + quantity + " units of " + goodsType);
    }
}
