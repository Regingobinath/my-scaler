package com.inventory.repositories;

import com.inventory.models.Address;
import com.inventory.models.DeliveryHub;
import com.inventory.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryHubRepository extends JpaRepository<DeliveryHub, Long> {
    public Optional<DeliveryHub> findByAddress_ZipCode(String zipCode);
}
