package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.integracija.example.model.Property;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {

}
