package sbnz.integracija.example.model.search;

import sbnz.integracija.example.model.persistance.Property;

public class ScoredProperty {

    private Property property;
    private double score;
    private Scaler scaler;

    public ScoredProperty(Property property) {
        this.property = property;
        this.scaler = new Scaler();
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Scaler getScaler() {
        return scaler;
    }

    public void setScaler(Scaler scaler) {
        this.scaler = scaler;
    }
}
