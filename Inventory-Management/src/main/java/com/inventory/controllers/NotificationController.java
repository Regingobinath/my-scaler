package com.inventory.controllers;


import com.inventory.dtos.*;
import com.inventory.models.Notification;
import com.inventory.services.NotificationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public RegisterUserForNotificationResponseDto registerUser(RegisterUserForNotificationRequestDto requestDto) {
        RegisterUserForNotificationResponseDto responseDto = new RegisterUserForNotificationResponseDto();
        try {
            Notification notification =
                    notificationService.registerUser(requestDto.getUserId(), requestDto.getProductId());
            responseDto.setNotification(notification);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }

    public DeregisterUserForNotificationResponseDto deregisterUser(DeregisterUserForNotificationRequestDto requestDto) {
        DeregisterUserForNotificationResponseDto responseDto = new DeregisterUserForNotificationResponseDto();
        try {
            this.notificationService.deregisterUser(requestDto.getUserId(), requestDto.getNotificationId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
