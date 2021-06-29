package sbnz.integracija.example.service;

import java.util.UUID;

public interface SellingService {

    public boolean reserveProperty(UUID bId, UUID pId);

    public boolean cancelReservation(UUID bId, UUID pId);

}