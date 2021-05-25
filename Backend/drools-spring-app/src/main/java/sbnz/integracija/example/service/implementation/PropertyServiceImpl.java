package sbnz.integracija.example.service.implementation;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.model.Property;
import sbnz.integracija.example.repository.PropertyRepository;
import sbnz.integracija.example.service.PropertyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<Property> properties = propertyRepository.getAll();
        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for(Property property : properties) {
            PropertyDTO propertyDTO = new PropertyDTO();
            propertyDTO.setId(property.getId());
            propertyDTO.setBuildDate(property.getBuildDate());
            propertyDTO.setCoordinates(property.getCoordinates());
            propertyDTO.setAddress(property.getAddress());
            propertyDTO.setOwner(property.getOwner());
            propertyDTO.setSurface(property.getSurface());
            propertyDTO.setPricePerSquareM(property.getPricePerSquareM());
            propertyDTO.setNumberOfRooms(property.getNumberOfRooms());
            propertyDTOs.add(propertyDTO);
        }
        return propertyDTOs;
    }

}
