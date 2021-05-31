package sbnz.integracija.example.model.persistance;

import org.hibernate.annotations.GenericGenerator;
import sbnz.integracija.example.model.enums.ObjectOfInterestType;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "object_of_interest")
public class ObjectOfInterest {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID", strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Embedded
    private Coordinates coordinates;

    @Enumerated(EnumType.STRING)
    private ObjectOfInterestType type;

    public ObjectOfInterest(UUID id, Coordinates coordinates, ObjectOfInterestType type) {
        this.id = id;
        this.coordinates = coordinates;
        this.type = type;
    }

    public ObjectOfInterest() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ObjectOfInterestType getType() {
        return type;
    }

    public void setType(ObjectOfInterestType type) {
        this.type = type;
    }

    public double calculateDistance(Property other) {
        return this.coordinates.calculateDistance(other.getCoordinates());
    }

    public double calculateDistance(ObjectOfInterest other) {
        return this.coordinates.calculateDistance(other.getCoordinates());
    }
}
