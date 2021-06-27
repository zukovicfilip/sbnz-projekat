package sbnz.integracija.example.service;

import sbnz.integracija.example.controller.dtos.FilterDTO;
import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.controller.dtos.ScoreDTO;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.search.ScoredProperty;

import java.util.List;
import java.util.UUID;

public interface PropertyService {

    List<Property> getAllProperties();

    List<Property> getPropertiesBySeller(UUID id);

    Property addProperty(Property property);

    List<ScoredProperty> scoreProperties(ScoreDTO scoreDTO, List<Property> properties);

    List<Property> filterProperties(FilterDTO filterDTO);

}
