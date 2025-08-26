package com.inventory.services;

import com.inventory.apiAdapter.EmailAdapter;
import com.inventory.exceptions.ProductNotFoundException;
import com.inventory.exceptions.UnAuthorizedAccessException;
import com.inventory.exceptions.UserNotFoundException;
import com.inventory.models.*;
import com.inventory.repositories.InventoryRepository;
import com.inventory.repositories.NotificationRepository;
import com.inventory.repositories.ProductRepository;
import com.inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final EmailAdapter emailAdapter;
    @Autowired
    public InventoryServiceImpl(
            InventoryRepository inventoryRepository, ProductRepository productRepository,
            UserRepository userRepository, NotificationRepository notificationRepository,
            EmailAdapter emailAdapter){
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.emailAdapter = emailAdapter;
    }


    @Override
    @Transactional
    public Inventory createOrUpdateInventory(int userId, int productId, int quantity) throws ProductNotFoundException, UserNotFoundException, UnAuthorizedAccessException {
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOp.get();
        if(!user.getUserType().equals(UserType.ADMIN)){
            throw new UnAuthorizedAccessException("You have no access");
        }

        Optional<Product> productOp = productRepository.findById(productId);
        if(productOp.isEmpty()){
            throw new ProductNotFoundException("Product id not found");
        }
        Product product = productOp.get();
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElse(new Inventory());
        inventory.setProduct(product);
        inventory.setQuantity(inventory.getQuantity()+quantity);//adding quantity in previous inventory quantity
        return inventoryRepository.save(inventory);
    }

    @Override
    @Transactional
    public void deleteInventory(int userId, int productId) throws UserNotFoundException, UnAuthorizedAccessException {
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOp.get();
        if(!user.getUserType().equals(UserType.ADMIN)){
            throw new UnAuthorizedAccessException("You have no access");
        }

        inventoryRepository.deleteByProductId(productId);
    }

    @Override
    @Transactional
    public Inventory updateInventory(int productId, int quantity) throws ProductNotFoundException {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        Optional<Inventory> inventoryOptional = this.inventoryRepository.findByProductId(product.getId());
        Inventory inventory;
        if(inventoryOptional.isEmpty()){
            inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setQuantity(quantity);
            return this.inventoryRepository.save(inventory);
        }
        inventory = inventoryOptional.get();
        inventory.setQuantity(inventory.getQuantity() + quantity);
        inventory = this.inventoryRepository.save(inventory);

        List<Notification> notifications
                 = this.notificationRepository.findAllByProductId(productId);

        Consumer<Notification> notificationConsumer = (notification) -> {
            this.emailAdapter.sendEmailAsync(
                    notification.getUser().getEmail(),
                    notification.getProduct().getName()+" is back in stock!",
                    "Dear "+notification.getUser().getName()+", "+
                            notification.getProduct().getName()+" is now back in stock. Grab it ASAP!");
        };

        notifications.forEach(n -> {
            notificationConsumer.accept(n);
            n.setStatus(NotificationStatus.SENT);
            this.notificationRepository.save(n);
        });

        return inventory;
    }
}