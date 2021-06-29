package sbnz.integracija.example.service;

import sbnz.integracija.example.controller.dtos.AdviceDTO;
import sbnz.integracija.example.controller.dtos.FilterDTO;
import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.controller.dtos.ScoreDTO;
import sbnz.integracija.example.model.events.DetailsEvent;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.search.ScoredProperty;

import java.util.List;
import java.util.UUID;

public interface PropertyService {

    List<Property> getAllProperties();

    void newDetailsEvent(DetailsEvent detailsEvent);

    AdviceDTO getAdvice(UUID id);

    void reserveProperty(UUID bId, UUID pId);

    Property getPropertyById(UUID id);

    List<Property> getPropertiesBySeller(UUID id);

    Property recommendPrice(Property property);

    Property addProperty(Property property);

    List<ScoredProperty> scoreProperties(ScoreDTO scoreDTO, List<Property> properties);

    List<Property> filterProperties(FilterDTO filterDTO);

}
