package db;

import entities.Donation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DonationDatabase {
    private List<Donation> donations;

    public DonationDatabase() {
        donations = new ArrayList<>();
    }

public boolean addDonation(Donation donation) {
    donations.add(donation);
    return true;
}

    public List<Donation> getDonationsByDonor(String donorName) {
        return donations.stream()
                .filter(d -> d.getDonorName().equalsIgnoreCase(donorName))
                .collect(Collectors.toList());
    }

    public List<Donation> getAllDonations() {
        return donations;
    }

    public double getTotalMonetaryDonations() {
        return donations.stream()
                .filter(d -> d.getMonetaryAmount() > 0)
                .mapToDouble(Donation::getMonetaryAmount)
                .sum();
    }

    public boolean withdrawDonation(double amount) {
        double totalMonetary = getTotalMonetaryDonations();
        
        if (amount > totalMonetary) {
            return false; 
        }

        addDonation(new Donation("Admin", amount));
        return true; 
    }
}
