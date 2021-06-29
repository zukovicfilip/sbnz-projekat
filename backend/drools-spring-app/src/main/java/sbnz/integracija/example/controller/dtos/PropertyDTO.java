package sbnz.integracija.example.controller.dtos;

import sbnz.integracija.example.model.enums.PropertyStatus;
import sbnz.integracija.example.model.persistance.Coordinates;
import sbnz.integracija.example.model.persistance.User;

import java.util.UUID;

public class PropertyDTO {
    private UUID id;
    private double surface;
    private Coordinates coordinates;
    private String address;
    private double pricePerSquareM;
    private int numberOfRooms;
    private int buildDate;
    private PropertyStatus propertyStatus;
    private User owner;

    public PropertyDTO() {
    }

    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getPricePerSquareM() {
        return pricePerSquareM;
    }

    public void setPricePerSquareM(double pricePerSquareM) {
        this.pricePerSquareM = pricePerSquareM;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(int buildDate) {
        this.buildDate = buildDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
