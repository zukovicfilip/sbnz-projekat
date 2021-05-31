package sbnz.integracija.example.model.persistance;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "property")
public class Property {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID", strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private double surface;

    @Embedded
    private Coordinates coordinates;

    private String address;

    private double pricePerSquareM;

    private int numberOfRooms;

    private int buildDate;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<Amenity> amenities;

    public Property() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
