package com.example.notification_managements.service;

import com.example.notification_managements.dto.NotificationRequest;
import com.example.notification_managements.dto.NotificationResponse;
import com.example.notification_managements.model.Notification;

import java.util.List;

public interface NotificationService {

    NotificationResponse createNotification(NotificationRequest request);

    Notification getNotificationById(Long id);

    List<Notification> getNotificationsByUserId(Long userId);

     void sendNotificationsToAllUsers(String recipient, String message);
}
