package sbnz.integracija.example.service.implementation;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.controller.dtos.CommissionDTO;
import sbnz.integracija.example.model.enums.PropertyStatus;
import sbnz.integracija.example.model.enums.ReservationStatus;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.persistance.PropertyReservation;
import sbnz.integracija.example.model.persistance.User;
import sbnz.integracija.example.repository.PropertyRepository;
import sbnz.integracija.example.repository.PropertyReservationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.SellingService;

import java.util.List;
import java.util.UUID;

@Service
public class SellingServiceImpl implements SellingService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyReservationRepository propertyReservationRepository;

    @Autowired
    private UserRepository userRepository;

    private final KieContainer kieContainer;

    @Autowired
    public SellingServiceImpl(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public PropertyReservation getPendingPropertyReservation(UUID propertyId) {
        return propertyReservationRepository.getPendingReservation(propertyId);
    }

    @Override
    public boolean reserveProperty(UUID bId, UUID pId) {

        Property property = propertyRepository.getById(pId);
        User user = userRepository.findById(bId).get();
        if(property == null || user == null)
            return false;

        propertyRepository.changePropertyStatus(pId, PropertyStatus.RESERVED);

        PropertyReservation propertyReservation = new PropertyReservation();
        propertyReservation.setProperty(property);
        propertyReservation.setUser(user);
        propertyReservation.setReservationStatus(ReservationStatus.PENDING);
        propertyReservationRepository.save(propertyReservation);

        return true;
    }

    @Override
    public boolean cancelReservation(UUID bId, UUID pId) {
        Property property = propertyRepository.getById(pId);
        User user = userRepository.findById(bId).get();
        if(property == null || user == null)
            return false;

        propertyRepository.changePropertyStatus(pId, PropertyStatus.FOR_SALE);
        propertyReservationRepository.setReservationStatus(bId, pId, ReservationStatus.CANCELED);

        return true;
    }

    @Override
    public CommissionDTO resolveReservation(UUID bId, UUID pId) {
        Property property = propertyRepository.getById(pId);
        User user = userRepository.findById(bId).get();
        if(property == null || user == null)
            return null;

        propertyRepository.changePropertyStatus(pId, PropertyStatus.SOLD);
        propertyReservationRepository.setReservationStatus(bId, pId, ReservationStatus.RESOLVED);

        List<Property> properties = propertyRepository.getAllSoldFromSeller(property.getOwner().getId());
        CommissionDTO commissionDTO = new CommissionDTO();

        commissionDTO.setTotalPrice(property.getPricePerSquareM() * property.getSurface());

        KieSession kieSession = kieContainer.getKieBase("Rulset5").newKieSession();
        kieSession.insert(properties);
        kieSession.insert(commissionDTO);
        kieSession.getAgenda().getAgendaGroup("commission").setFocus();
        System.out.println(kieSession.fireAllRules());
        kieSession.dispose();

        return commissionDTO;
    }

    @Override
    public boolean refuseReservation(UUID bId, UUID pId) {
        Property property = propertyRepository.getById(pId);
        User user = userRepository.findById(bId).get();
        if(property == null || user == null)
            return false;

        propertyRepository.changePropertyStatus(pId, PropertyStatus.FOR_SALE);
        propertyReservationRepository.setReservationStatus(bId, pId, ReservationStatus.REFUSED);

        return true;
    }

}
