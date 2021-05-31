package sbnz.integracija.example.model.search;

import java.util.ArrayList;
import java.util.List;

public class Scaler {

    private double distanceScale = 1.0;
    private double schoolScale = 1.0;
    private double universityScale = 1.0;
    private double kindergartenScale = 1.0;
    private double gymScale = 1.0;
    private double parkScale = 1.0;
    private double hospitalScale = 1.0;
    private double bankScale = 1.0;
    private double supermarketScale = 1.0;
    private double shoppingCenterScale = 1.0;
    private double swimmingPoolScale = 1.0;
    private double nightClubScale = 1.0;
    private double cinemaScale = 1.0;
    private double restaurantScale = 1.0;

    private List<String> firedRules = new ArrayList<>();

    public Scaler() {
    }

    public double getDistanceScale() {
        return distanceScale;
    }

    public void setDistanceScale(double distanceScale) {
        this.distanceScale = distanceScale;
    }

    public double getSchoolScale() {
        return schoolScale;
    }

    public void setSchoolScale(double schoolScale) {
        this.schoolScale = schoolScale;
    }

    public double getUniversityScale() {
        return universityScale;
    }

    public void setUniversityScale(double universityScale) {
        this.universityScale = universityScale;
    }

    public double getKindergartenScale() {
        return kindergartenScale;
    }

    public void setKindergartenScale(double kindergartenScale) {
        this.kindergartenScale = kindergartenScale;
    }

    public double getGymScale() {
        return gymScale;
    }

    public void setGymScale(double gymScale) {
        this.gymScale = gymScale;
    }

    public double getParkScale() {
        return parkScale;
    }

    public void setParkScale(double parkScale) {
        this.parkScale = parkScale;
    }

    public double getHospitalScale() {
        return hospitalScale;
    }

    public void setHospitalScale(double hospitalScale) {
        this.hospitalScale = hospitalScale;
    }

    public double getBankScale() {
        return bankScale;
    }

    public void setBankScale(double bankScale) {
        this.bankScale = bankScale;
    }

    public double getSupermarketScale() {
        return supermarketScale;
    }

    public void setSupermarketScale(double supermarketScale) {
        this.supermarketScale = supermarketScale;
    }

    public double getShoppingCenterScale() {
        return shoppingCenterScale;
    }

    public void setShoppingCenterScale(double shoppingCenterScale) {
        this.shoppingCenterScale = shoppingCenterScale;
    }

    public double getSwimmingPoolScale() {
        return swimmingPoolScale;
    }

    public void setSwimmingPoolScale(double swimmingPoolScale) {
        this.swimmingPoolScale = swimmingPoolScale;
    }

    public double getNightClubScale() {
        return nightClubScale;
    }

    public void setNightClubScale(double nightClubScale) {
        this.nightClubScale = nightClubScale;
    }

    public double getCinemaScale() {
        return cinemaScale;
    }

    public void setCinemaScale(double cinemaScale) {
        this.cinemaScale = cinemaScale;
    }

    public double getRestaurantScale() {
        return restaurantScale;
    }

    public void setRestaurantScale(double restaurantScale) {
        this.restaurantScale = restaurantScale;
    }

    public List<String> getFiredRules() {
        return firedRules;
    }

    public void setFiredRules(List<String> firedRules) {
        this.firedRules = firedRules;
    }
}
