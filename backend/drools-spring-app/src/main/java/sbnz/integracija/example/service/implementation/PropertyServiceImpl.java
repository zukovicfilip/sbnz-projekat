package sbnz.integracija.example.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.controller.dtos.ScoredPropertyDTO;
import sbnz.integracija.example.controller.dtos.SearchDTO;
import sbnz.integracija.example.model.persistance.ObjectOfInterest;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.search.ScoredProperties;
import sbnz.integracija.example.model.search.ScoredProperty;
import sbnz.integracija.example.repository.ObjectOfInterestRepository;
import sbnz.integracija.example.repository.PropertyRepository;
import sbnz.integracija.example.service.PropertyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final KieContainer kieContainer;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ObjectOfInterestRepository objectOfInterestRepository;

    @Autowired
    public PropertyServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

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

    @Override
    public List<ScoredPropertyDTO> searchProperties(SearchDTO searchDTO) {
        System.out.println("Started method in service");
        List<Property> properties = propertyRepository.getAll();
        List<ScoredProperty> scoredPropertyList = new ArrayList<>();
        for(Property property : properties) {
            scoredPropertyList.add(new ScoredProperty(property));
        }
        List<ObjectOfInterest> objectsOfInterest = objectOfInterestRepository.findAll();
        ScoredProperties scoredProperties = new ScoredProperties(new ArrayList<>());

        //KieSession kieSession = kieContainer.getKieBase("KBase2").newKieSession();
        KieSession kieSession = kieContainer.newKieSession();
        scoredPropertyList.forEach(kieSession::insert);
        objectsOfInterest.forEach(kieSession::insert);
        kieSession.insert(scoredProperties);
        kieSession.insert(searchDTO);
        System.out.println("Inserted in kia session");

        kieSession.getAgenda().getAgendaGroup("finishing").setFocus();
        kieSession.getAgenda().getAgendaGroup("distance_score_calculation").setFocus();
        kieSession.getAgenda().getAgendaGroup("scaling").setFocus();
        System.out.println(kieSession.fireAllRules());
        System.out.println("Fired all rules");
        kieSession.dispose();

        List<ScoredPropertyDTO> scoredPropertyDTOS = new ArrayList<>();
        for(ScoredProperty scoredProperty : scoredProperties.getScoredProperties()) {
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

        scoredProperties.getScoredProperties().forEach(
                ps -> System.out.println(ps.getScore()));
        return scoredPropertyDTOS;
    }

}
