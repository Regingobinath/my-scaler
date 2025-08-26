package com.inventory.repositories;

import com.inventory.models.Inventory;
import com.inventory.models.Notification;
import com.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductId(long productId);
    void deleteByProductId(long productId);
    Optional<Inventory> findByProduct(Product product);
}