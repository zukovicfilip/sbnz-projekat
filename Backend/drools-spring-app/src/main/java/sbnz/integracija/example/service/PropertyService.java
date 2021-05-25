package sbnz.integracija.example.service;

import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.model.Property;

import java.util.List;

public interface PropertyService {

    List<PropertyDTO> getAllProperties();

}
