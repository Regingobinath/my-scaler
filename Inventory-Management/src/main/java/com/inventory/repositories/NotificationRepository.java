package com.inventory.repositories;

import com.inventory.models.Inventory;
import com.inventory.models.Notification;
import com.inventory.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    public List<Notification> findAllByProductId(long productId);
    List<Notification> findByProduct(Product product);
    public void delete(Notification notification);
    public Optional<Notification> findById(long id);
}
