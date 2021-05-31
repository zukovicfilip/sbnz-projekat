package sbnz.integracija.example.service;

import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.controller.dtos.ScoredPropertyDTO;
import sbnz.integracija.example.controller.dtos.SearchDTO;
import sbnz.integracija.example.model.search.ScoredProperty;

import java.util.List;

public interface PropertyService {

    List<PropertyDTO> getAllProperties();

    List<ScoredPropertyDTO> searchProperties(SearchDTO searchDTO);

}
