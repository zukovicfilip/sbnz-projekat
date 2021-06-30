package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sbnz.integracija.example.model.enums.PropertyStatus;
import sbnz.integracija.example.model.persistance.Property;

import java.util.List;
import java.util.UUID;

public interface PropertyRepository extends JpaRepository<Property, UUID> {

    @Query("SELECT p FROM property p JOIN FETCH p.owner o")
    List<Property> getAll();

    @Query("SELECT p FROM property p JOIN FETCH p.owner o WHERE p.id = :id")
    Property getById(UUID id);

    @Query("SELECT p FROM property p JOIN FETCH p.owner o WHERE p.owner.id = :sellerId")
    List<Property> getBySellerId(UUID sellerId);

    @Transactional
    @Modifying
    @Query("UPDATE property p SET p.propertyStatus = :propertyStatus WHERE p.id = :propertyId")
    void changePropertyStatus(UUID propertyId, PropertyStatus propertyStatus);

    @Query("SELECT p FROM property p JOIN FETCH p.owner o WHERE o.id = :sellerId and p.propertyStatus = 'SOLD'")
    List<Property> getAllSoldFromSeller(UUID sellerId);

}
