package sbnz.integracija.example.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.controller.dtos.FilterDTO;
import sbnz.integracija.example.controller.dtos.PropertyDTO;
import sbnz.integracija.example.controller.dtos.ScoreDTO;
import sbnz.integracija.example.model.persistance.ObjectOfInterest;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.search.FilteredProperties;
import sbnz.integracija.example.model.search.ScoredProperties;
import sbnz.integracija.example.model.search.ScoredProperty;
import sbnz.integracija.example.repository.ObjectOfInterestRepository;
import sbnz.integracija.example.repository.PropertyRepository;
import sbnz.integracija.example.service.PropertyService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public List<Property> getAllProperties() {
        return propertyRepository.getAll();
    }

    @Override
    public List<Property> getPropertiesBySeller(UUID id) {
        return propertyRepository.getBySellerId(id);
    }

    @Override
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    public List<Property> filterProperties(FilterDTO filterDTO) {
        List<Property> properties = propertyRepository.getAll();
        FilteredProperties filteredProperties = new FilteredProperties(new ArrayList<>());
        System.out.println(properties.get(0).getId() == properties.get(0).getId());

        KieSession kieSession = kieContainer.getKieBase("Rulset2").newKieSession();
        properties.forEach(kieSession::insert);
        kieSession.insert(filteredProperties);
        kieSession.insert(filterDTO);

        kieSession.getAgenda().getAgendaGroup("filter").setFocus();
        System.out.println(kieSession.fireAllRules());
        kieSession.dispose();

        return filteredProperties.getProperties();
    }

    @Override
    public List<ScoredProperty> scoreProperties(ScoreDTO searchDTO, List<Property> properties) {
        if(properties == null)
            properties = propertyRepository.getAll();
        List<ScoredProperty> scoredPropertyList = new ArrayList<>();
        for(Property property : properties) {
            scoredPropertyList.add(new ScoredProperty(property));
        }
        List<ObjectOfInterest> objectsOfInterest = objectOfInterestRepository.findAll();
        ScoredProperties scoredProperties = new ScoredProperties(new ArrayList<>());

        KieSession kieSession = kieContainer.getKieBase("Rulset1").newKieSession();
        scoredPropertyList.forEach(kieSession::insert);
        objectsOfInterest.forEach(kieSession::insert);
        kieSession.insert(scoredProperties);
        kieSession.insert(searchDTO);

        kieSession.getAgenda().getAgendaGroup("finishing").setFocus();
        kieSession.getAgenda().getAgendaGroup("distance_score_calculation").setFocus();
        kieSession.getAgenda().getAgendaGroup("scaling").setFocus();
        System.out.println(kieSession.fireAllRules());
        kieSession.dispose();

        return scoredProperties.getScoredProperties();
    }

}
