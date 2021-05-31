package sbnz.integracija.example.controller.dtos;

import sbnz.integracija.example.model.enums.Occupations;

import java.util.List;

public class SearchDTO {

    public int younger;

    public int middleAged;

    public int older;

    public boolean kids;

    private boolean hasCar;

    private List<Occupations> occupations;

    public SearchDTO(int younger, int middleAged, int older, boolean kids, boolean hasCar, List<Occupations> occupations) {
        this.younger = younger;
        this.middleAged = middleAged;
        this.older = older;
        this.kids = kids;
        this.hasCar = hasCar;
        this.occupations = occupations;
    }

    public SearchDTO() {
    }

    public int getYounger() {
        return younger;
    }

    public void setYounger(int younger) {
        this.younger = younger;
    }

    public int getMiddleAged() {
        return middleAged;
    }

    public void setMiddleAged(int middleAged) {
        this.middleAged = middleAged;
    }

    public int getOlder() {
        return older;
    }

    public void setOlder(int older) {
        this.older = older;
    }

    public boolean isKids() {
        return kids;
    }

    public void setKids(boolean kids) {
        this.kids = kids;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public void setHasCar(boolean hasCar) {
        this.hasCar = hasCar;
    }

    public List<Occupations> getOccupations() {
        return occupations;
    }

    public void setOccupations(List<Occupations> occupations) {
        this.occupations = occupations;
    }
}
