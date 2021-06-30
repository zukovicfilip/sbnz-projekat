package sbnz.integracija.example.controller;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import sbnz.integracija.example.controller.dto_mappers.DTOMapper;
import sbnz.integracija.example.controller.dtos.*;
import sbnz.integracija.example.model.FileInfo;
import sbnz.integracija.example.model.enums.PropertyStatus;
import sbnz.integracija.example.model.events.DetailsEvent;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.persistance.PropertyReservation;
import sbnz.integracija.example.model.persistance.User;
import sbnz.integracija.example.model.search.ScoredProperty;
import sbnz.integracija.example.service.FilesStorageService;
import sbnz.integracija.example.service.PropertyService;
import sbnz.integracija.example.service.SellingService;
import sbnz.integracija.example.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private SellingService sellingService;

    @Autowired
    private UserService userService;

    @Autowired
    FilesStorageService storageService;

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();

        List<PropertyDTO> propertyDTOs = new ArrayList<>();
        for(Property property : properties) {
            propertyDTOs.add(DTOMapper.getPropertyDTO(property));
        }

        return new ResponseEntity<>(propertyDTOs, HttpStatus.OK);
    }

    @PostMapping("/details-event")
    public ResponseEntity newDetailsEvent(@RequestBody DetailsEvent detailsEvent) {
        propertyService.newDetailsEvent(detailsEvent);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/advice")
    public ResponseEntity<AdviceDTO> getAdvice(@PathVariable UUID id) {

        AdviceDTO adviceDTO = propertyService.getAdvice(id);

        return new ResponseEntity<>(adviceDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable UUID id) {
        Property property = propertyService.getPropertyById(id);
        PropertyDTO propertyDTO = DTOMapper.getPropertyDTO(property);
        if(property.getPropertyStatus() == PropertyStatus.RESERVED) {
            PropertyReservation propertyReservation = sellingService.getPendingPropertyReservation(id);
            User user = userService.getUserById(propertyReservation.getUser().getId());
            propertyDTO.setReservedBy(user);
        }

        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        List<FileInfo> propertyFileInfos = new ArrayList<>();

        for(FileInfo fileInfo : fileInfos)
            if(fileInfo.getName().contains(id.toString()))
                propertyFileInfos.add(fileInfo);

        propertyDTO.setImages(propertyFileInfos);

        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);
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
            PropertyDTO propertyDTO = DTOMapper.getPropertyDTO(scoredProperty.getProperty());
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
            PropertyDTO propertyDTO = DTOMapper.getPropertyDTO(scoredProperty.getProperty());
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
