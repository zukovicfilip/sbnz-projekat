package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sbnz.integracija.example.model.persistance.Property;

import java.util.List;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {

    @Query("SELECT p FROM property p JOIN FETCH p.owner o")
    List<Property> getAll();

    @Query("SELECT p FROM property p JOIN FETCH p.owner o WHERE p.owner.id = :sellerId")
    List<Property> getBySellerId(UUID sellerId);

}
