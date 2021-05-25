package sbnz.integracija.example.controller.dtos;

import sbnz.integracija.example.model.Amenity;
import sbnz.integracija.example.model.Coordinates;
import sbnz.integracija.example.model.User;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.UUID;

public class PropertyDTO {
    private UUID id;
    private double surface;
    private Coordinates coordinates;
    private double pricePerSquareM;
    private int numberOfRooms;
    private int buildDate;
    private User owner;

    public PropertyDTO(UUID id, double surface, Coordinates coordinates, double pricePerSquareM, int numberOfRooms, int buildDate, User owner) {
        this.id = id;
        this.surface = surface;
        this.coordinates = coordinates;
        this.pricePerSquareM = pricePerSquareM;
        this.numberOfRooms = numberOfRooms;
        this.buildDate = buildDate;
        this.owner = owner;
    }

    public PropertyDTO() {
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
}