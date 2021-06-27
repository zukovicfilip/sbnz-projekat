package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.controller.dto_mappers.DTOMapper;
import sbnz.integracija.example.controller.dtos.*;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.search.ScoredProperty;
import sbnz.integracija.example.service.PropertyService;
import sbnz.integracija.example.service.UserService;

import java.util.*;

@RestController
@RequestMapping(value = "/api/seller")
public class SellerController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/property")
    public ResponseEntity<List<PropertyDTO>> getPropertiesBySeller(@PathVariable UUID id) {
        List<Property> properties = propertyService.getPropertiesBySeller(id);

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

        return new ResponseEntity<>(propertyDTOs, HttpStatus.OK);
    }

    @PostMapping("/{id}/property")
    public ResponseEntity<PropertyDTO> addProperty(@PathVariable UUID id, @RequestBody Property property) {

        property.setOwner(userService.getUserById(id));
        property = propertyService.addProperty(property);

        return new ResponseEntity<>(DTOMapper.getPropertyDTO(property), HttpStatus.OK);
    }

}
