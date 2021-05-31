package sbnz.integracija.example.controller.dtos;

import sbnz.integracija.example.model.persistance.Property;

public class ScoredPropertyDTO {

    private PropertyDTO property;
    private double score;

    public ScoredPropertyDTO(PropertyDTO property, double score) {
        this.property = property;
        this.score = score;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
