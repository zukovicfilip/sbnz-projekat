package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sbnz.integracija.example.model.enums.ReservationStatus;
import sbnz.integracija.example.model.persistance.PropertyReservation;

import java.util.UUID;

public interface PropertyReservationRepository extends JpaRepository<PropertyReservation, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE property_reservation pr SET pr.reservationStatus = :reservationStatus WHERE pr.property.id = :pId AND pr.user.id = :bId AND pr.reservationStatus = 'PENDING'")
    void setReservationStatus(UUID bId, UUID pId, ReservationStatus reservationStatus);

    @Query("SELECT pr FROM property_reservation pr WHERE pr.property.id = :propertyId AND pr.reservationStatus = 'PENDING'")
    PropertyReservation getPendingReservation(UUID propertyId);

}
