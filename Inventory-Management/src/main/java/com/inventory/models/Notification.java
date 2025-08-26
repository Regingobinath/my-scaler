package com.inventory.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Notification extends BaseModel{
    @OneToOne
    private Product product;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
}
