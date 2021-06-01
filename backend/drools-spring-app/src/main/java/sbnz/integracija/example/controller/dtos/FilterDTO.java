package sbnz.integracija.example.controller.dtos;

public class FilterDTO {

    private double surfaceMin;

    private double surfaceMax;

    private double priceMin;

    private double priceMax;

    private int numberOfRoomsMin;

    private int numberOfRoomsMax;

    public FilterDTO(double surfaceMin, double surfaceMax, double priceMin, double priceMax, int numberOfRoomsMin, int numberOfRoomsMax) {
        this.surfaceMin = surfaceMin;
        this.surfaceMax = surfaceMax;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.numberOfRoomsMin = numberOfRoomsMin;
        this.numberOfRoomsMax = numberOfRoomsMax;
    }

    public FilterDTO() {
    }

    public double getSurfaceMin() {
        return surfaceMin;
    }

    public void setSurfaceMin(double surfaceMin) {
        this.surfaceMin = surfaceMin;
    }

    public double getSurfaceMax() {
        return surfaceMax;
    }

    public void setSurfaceMax(double surfaceMax) {
        this.surfaceMax = surfaceMax;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(double priceMax) {
        this.priceMax = priceMax;
    }

    public int getNumberOfRoomsMin() {
        return numberOfRoomsMin;
    }

    public void setNumberOfRoomsMin(int numberOfRoomsMin) {
        this.numberOfRoomsMin = numberOfRoomsMin;
    }

    public int getNumberOfRoomsMax() {
        return numberOfRoomsMax;
    }

    public void setNumberOfRoomsMax(int numberOfRoomsMax) {
        this.numberOfRoomsMax = numberOfRoomsMax;
    }
}
