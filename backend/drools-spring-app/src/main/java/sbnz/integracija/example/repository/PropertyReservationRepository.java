package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.integracija.example.model.persistance.Property;
import sbnz.integracija.example.model.persistance.PropertyReservation;

import java.util.UUID;

public interface PropertyReservationRepository extends JpaRepository<PropertyReservation, UUID> {



}
