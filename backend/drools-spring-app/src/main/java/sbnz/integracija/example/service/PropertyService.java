package sbnz.integracija.example.service;

import sbnz.integracija.example.controller.dtos.FilterDTO;
import sbnz.integracija.example.controller.dtos.ScoreDTO;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.search.ScoredProperty;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties();

    List<ScoredProperty> scoreProperties(ScoreDTO scoreDTO, List<Property> properties);

    List<Property> filterProperties(FilterDTO filterDTO);

}
