package entities;

import java.util.Date;

public class Donation {
    private String donorName;
    private double monetaryAmount;
    private DonationCategory itemCategory;
    private double itemQuantity;
    private String description;
    private Date donationDate;

    public enum DonationCategory {
        FOOD, 
        CLOTHING, 
        MEDICAL_SUPPLIES, 
        HYGIENE_PRODUCTS;
    }

    public Donation(String donorName, double monetaryAmount) {
        this.donorName = donorName;
        this.monetaryAmount = monetaryAmount;
        this.itemCategory = null;
        this.itemQuantity = 0;
        this.description = "Monetary Donation";
        this.donationDate = new Date();
    }

    public Donation(String donorName, DonationCategory itemCategory, double itemQuantity, String description) {
        this.donorName = donorName;
        this.monetaryAmount = 0;
        this.itemCategory = itemCategory;
        this.itemQuantity = itemQuantity;
        this.description = description;
        this.donationDate = new Date();
    }

    public String getDonorName() {
        return donorName;
    }

    public double getMonetaryAmount() {
        return monetaryAmount;
    }

    public DonationCategory getItemCategory() {
        return itemCategory;
    }

    public double getItemQuantity() {
        return itemQuantity;
    }

    public String getDescription() {
        return description;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    @Override
    public String toString() {
        if (monetaryAmount > 0) {
            return donorName + " donated " + monetaryAmount + " as a monetary donation on " + donationDate.toString() + ".";
        } else {
            return donorName + " donated " + itemQuantity + " units of " + itemCategory + " (" + description + ") on " + donationDate.toString() + ".";
        }
    }
}
