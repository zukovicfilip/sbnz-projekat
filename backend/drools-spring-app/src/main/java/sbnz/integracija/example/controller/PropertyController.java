package sbnz.integracija.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbnz.integracija.example.controller.dtos.FilterDTO;
import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.controller.dtos.ScoredPropertyDTO;
import sbnz.integracija.example.controller.dtos.SearchDTO;
import sbnz.integracija.example.model.search.ScoredProperty;
import sbnz.integracija.example.service.PropertyService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PropertyDTO>> filterProperties(@RequestBody FilterDTO filterDTO) {
        List<PropertyDTO> filterProperty = propertyService.filterProperties(filterDTO);

        return new ResponseEntity<>(filterProperty, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<ScoredPropertyDTO>> searchProperties(@RequestBody SearchDTO searchDTO) {
        List<ScoredPropertyDTO> scoredProperties = propertyService.searchProperties(searchDTO);

        return new ResponseEntity<>(scoredProperties, HttpStatus.OK);
    }

}
