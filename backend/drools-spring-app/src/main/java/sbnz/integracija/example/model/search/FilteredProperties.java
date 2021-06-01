package sbnz.integracija.example.model.search;

import sbnz.integracija.example.model.persistance.Property;

import java.util.List;

public class FilteredProperties {

    private List<Property> properties;

    public FilteredProperties(List<Property> properties) {
        this.properties = properties;
    }

    public FilteredProperties() {
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
