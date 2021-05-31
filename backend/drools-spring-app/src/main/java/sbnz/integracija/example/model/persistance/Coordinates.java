package sbnz.integracija.example.model.persistance;

import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {

    private double lat;

    private double lon;

    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Coordinates() {
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double calculateDistance(Coordinates other) {
        double theta = this.lon - other.lon;
        double dist = Math.sin(Math.toRadians(this.lat)) * Math.sin(Math.toRadians(other.lat)) +
                Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(other.lat)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist *= 60 * 1.1515 * 1.609344;
        return dist;
    }
}
