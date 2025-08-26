package com.inventory.repositories;

import com.inventory.models.Inventory;
import com.inventory.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository  extends JpaRepository<Seller, Long> {
}
