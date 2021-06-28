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

import java.util.*;

@RestController
@RequestMapping(value = "/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();

        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for(Property property : properties) {
            propertyDTOs.add(DTOMapper.getPropertyDTO(property));
        }

        return new ResponseEntity<>(propertyDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable UUID id) {
        Property property = propertyService.getPropertyById(id);

        return new ResponseEntity<>(DTOMapper.getPropertyDTO(property), HttpStatus.OK);
    }

    @PostMapping("/recommend-price")
    public ResponseEntity<PropertyDTO> recommendPrice(@RequestBody Property property) {
        property = propertyService.recommendPrice(property);

        if (property == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(DTOMapper.getPropertyDTO(property), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PropertyDTO>> filterProperties(@RequestBody FilterDTO filterDTO) {
        List<Property> filterProperty = propertyService.filterProperties(filterDTO);

        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for(Property property : filterProperty) {
            propertyDTOs.add(DTOMapper.getPropertyDTO(property));
        }

        return new ResponseEntity<>(propertyDTOs, HttpStatus.OK);
    }

    @PostMapping("/score")
    public ResponseEntity<List<ScoredPropertyDTO>> scoreProperties(@RequestBody ScoreDTO scoreDTO) {
        List<ScoredProperty> scoredProperties = propertyService.scoreProperties(scoreDTO, null);

        List<ScoredPropertyDTO> scoredPropertyDTOS = new ArrayList<>();
        for(ScoredProperty scoredProperty : scoredProperties) {
            Property property = scoredProperty.getProperty();
            PropertyDTO propertyDTO = new PropertyDTO();
            propertyDTO.setId(property.getId());
            propertyDTO.setBuildDate(property.getBuildDate());
            propertyDTO.setCoordinates(property.getCoordinates());
            propertyDTO.setAddress(property.getAddress());
            propertyDTO.setOwner(property.getOwner());
            propertyDTO.setSurface(property.getSurface());
            propertyDTO.setPricePerSquareM(property.getPricePerSquareM());
            propertyDTO.setNumberOfRooms(property.getNumberOfRooms());
            scoredPropertyDTOS.add(new ScoredPropertyDTO(propertyDTO, scoredProperty.getScore()));
        }

        Collections.sort(scoredPropertyDTOS, new Comparator<ScoredPropertyDTO>() {
            @Override
            public int compare(ScoredPropertyDTO sp1, ScoredPropertyDTO sp2) {
                if(sp1.getScore() > sp2.getScore())
                    return -1;
                else if(sp1.getScore() == sp2.getScore())
                    return 0;
                else
                    return 1;
            }
        });

        return new ResponseEntity<>(scoredPropertyDTOS, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ScoredPropertyDTO>> searchProperties(@RequestBody SearchDTO searchDTO) {
        List<Property> filteredProperties = propertyService.filterProperties(searchDTO.getFilterDTO());
        List<ScoredProperty> scoredProperties = propertyService.scoreProperties(searchDTO.getScoreDTO(), filteredProperties);

        List<ScoredPropertyDTO> scoredPropertyDTOS = new ArrayList<>();
        for(ScoredProperty scoredProperty : scoredProperties) {
            Property property = scoredProperty.getProperty();
            PropertyDTO propertyDTO = new PropertyDTO();
            propertyDTO.setId(property.getId());
            propertyDTO.setBuildDate(property.getBuildDate());
            propertyDTO.setCoordinates(property.getCoordinates());
            propertyDTO.setAddress(property.getAddress());
            propertyDTO.setOwner(property.getOwner());
            propertyDTO.setSurface(property.getSurface());
            propertyDTO.setPricePerSquareM(property.getPricePerSquareM());
            propertyDTO.setNumberOfRooms(property.getNumberOfRooms());
            scoredPropertyDTOS.add(new ScoredPropertyDTO(propertyDTO, scoredProperty.getScore()));
        }

        Collections.sort(scoredPropertyDTOS, new Comparator<ScoredPropertyDTO>() {
            @Override
            public int compare(ScoredPropertyDTO sp1, ScoredPropertyDTO sp2) {
                if(sp1.getScore() > sp2.getScore())
                    return -1;
                else if(sp1.getScore() == sp2.getScore())
                    return 0;
                else
                    return 1;
            }
        });

        return new ResponseEntity<>(scoredPropertyDTOS, HttpStatus.OK);
    }

}
