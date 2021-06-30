package sbnz.integracija.example.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.model.enums.PropertyStatus;
import sbnz.integracija.example.model.enums.ReservationStatus;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.persistance.PropertyReservation;
import sbnz.integracija.example.model.persistance.User;
import sbnz.integracija.example.repository.PropertyRepository;
import sbnz.integracija.example.repository.PropertyReservationRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.service.SellingService;

import java.util.UUID;

@Service
public class SellingServiceImpl implements SellingService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyReservationRepository propertyReservationRepository;

    @Autowired
    private UserRepository userRepository;

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
    public boolean resolveReservation(UUID bId, UUID pId) {
        Property property = propertyRepository.getById(pId);
        User user = userRepository.findById(bId).get();
        if(property == null || user == null)
            return false;

        propertyRepository.changePropertyStatus(pId, PropertyStatus.SOLD);
        propertyReservationRepository.setReservationStatus(bId, pId, ReservationStatus.RESOLVED);

        return true;
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
