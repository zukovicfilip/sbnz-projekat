package sbnz.integracija.example.model;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.LocalDate;

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

    private double pricePerSquareM;

    private int numberOfRooms;

    private LocalDate buildDate;

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

    public LocalDate getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(LocalDate buildDate) {
        this.buildDate = buildDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
