package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sbnz.integracija.example.model.persistance.ObjectOfInterest;

import java.util.UUID;

public interface ObjectOfInterestRepository extends JpaRepository<ObjectOfInterest, UUID> {
}
