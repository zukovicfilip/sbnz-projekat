package sbnz.integracija.example.controller.dtos;

import sbnz.integracija.example.model.enums.SellerCategory;

public class CommissionDTO {

    private double totalPrice;
    private double commission;
    private double commissionFee;

    private int numberOfSoldProperties;
    private SellerCategory sellerCategory;

    public CommissionDTO() {
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public double getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(double commissionFee) {
        this.commissionFee = commissionFee;
    }

    public int getNumberOfSoldProperties() {
        return numberOfSoldProperties;
    }

    public void setNumberOfSoldProperties(int numberOfSoldProperties) {
        this.numberOfSoldProperties = numberOfSoldProperties;
    }

    public SellerCategory getSellerCategory() {
        return sellerCategory;
    }

    public void setSellerCategory(SellerCategory sellerCategory) {
        this.sellerCategory = sellerCategory;
    }
}
