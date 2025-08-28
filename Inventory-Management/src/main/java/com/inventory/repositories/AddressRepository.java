package com.inventory.repositories;


import com.inventory.models.Address;
import com.inventory.models.Inventory;
import com.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findById(int addressId);
}
