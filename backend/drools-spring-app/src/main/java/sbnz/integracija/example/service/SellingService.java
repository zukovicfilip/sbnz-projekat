package sbnz.integracija.example.service;

import sbnz.integracija.example.controller.dtos.CommissionDTO;
import sbnz.integracija.example.model.persistance.PropertyReservation;

import java.util.UUID;

public interface SellingService {

    public PropertyReservation getPendingPropertyReservation(UUID propertyId);

    public boolean reserveProperty(UUID bId, UUID pId);

    public boolean cancelReservation(UUID bId, UUID pId);

    public CommissionDTO resolveReservation(UUID bId, UUID pId);

    public boolean refuseReservation(UUID bId, UUID pId);

}
