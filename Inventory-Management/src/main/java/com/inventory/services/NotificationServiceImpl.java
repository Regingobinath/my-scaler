package com.inventory.services;

import com.inventory.exceptions.*;
import com.inventory.models.*;
import com.inventory.repositories.InventoryRepository;
import com.inventory.repositories.NotificationRepository;
import com.inventory.repositories.ProductRepository;
import com.inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    @Autowired
    public NotificationServiceImpl(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            NotificationRepository notificationRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }
    @Override
    @Transactional
    public Notification registerUser(int userId, int productId) throws UserNotFoundException, ProductNotFoundException, ProductInStockException {
        Optional<User> user = this.userRepository.findById(userId);
        user.orElseThrow(() -> new UserNotFoundException("User not found"));

        Optional<Inventory> inventory = this.inventoryRepository.findByProductId(productId);
        inventory.orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (inventory.get().getQuantity() > 0) {
            throw new ProductInStockException("Product is in stock");
        }

        Optional<Product> product = this.productRepository.findById(productId);

        Notification notification = new Notification();
        notification.setUser(user.get());
        notification.setProduct(product.get());
        notification.setStatus(NotificationStatus.PENDING);
        notification = notificationRepository.save(notification);

        return notification;
    }

    @Override
    @Transactional
    public void deregisterUser(int userId, int notificationId) throws UserNotFoundException, NotificationNotFoundException, UnAuthorizedException {
        Optional<User> user = this.userRepository.findById(userId);
        user.orElseThrow(() -> new UserNotFoundException("User not found"));

        Optional<Notification> notification = this.notificationRepository.findById(Long.valueOf(notificationId));
        notification.orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
        if(notification.get().getUser().getId() != userId) {
            throw new UnAuthorizedException("User UnAuthorized");
        }
        this.notificationRepository.deleteById(notificationId);
    }
}
