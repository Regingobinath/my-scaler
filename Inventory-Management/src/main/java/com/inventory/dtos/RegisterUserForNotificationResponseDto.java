package com.inventory.dtos;

import com.inventory.models.Notification;
import lombok.Data;

@Data
public class RegisterUserForNotificationResponseDto {
    private Notification notification;
    private ResponseStatus responseStatus;
}
