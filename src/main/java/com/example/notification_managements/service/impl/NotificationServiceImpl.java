package com.example.notification_managements.service.impl;

import com.example.notification_managements.client.UserClient;
import com.example.notification_managements.exception.NotificationNotFoundException;
import com.example.notification_managements.dto.NotificationRequest;
import com.example.notification_managements.dto.NotificationResponse;
import com.example.notification_managements.exception.NotificationServiceException;
import com.example.notification_managements.mapper.NotificationMapper;
import com.example.notification_managements.model.Notification;
import com.example.notification_managements.repository.NotificationRepository;
import com.example.notification_managements.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final EmailNotificationService emailNotificationService;
    private final UserClient userClient;

    @Override
    public NotificationResponse createNotification(@RequestBody NotificationRequest request) {
        log.info("Creating a new notification for user ");
        Notification notification = notificationMapper.requestToEntity(request);
        notification.setStatus("Pending");//default status
        notification.setSentTime(LocalDateTime.now());
        try {
            Notification savedNotification = notificationRepository.save(notification);
            log.info("Notification created successfully with ID: {}", savedNotification.getId());
            emailNotificationService.sendEmail(request.getRecipient(), "New Notification", request.getMessage());
            return notificationMapper.entityToResponse(savedNotification);
        } catch (Exception e) {
            log.error("Error occurred while creating notification: {}", e.getMessage());
            throw new NotificationServiceException("Failed to create the notification");
        }

    }

    @Override
    public Notification getNotificationById(Long id) {
        log.info("fetching notification with ID: {}", id);

        return notificationRepository.findById(id).orElseThrow(() -> {
            log.warn("Notification not found with ID: {}", id);
            return new NotificationNotFoundException("Notification not found with id: " + id);
        });
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {
        log.info("Fetching notifications for user ID: {}", userId);
        List<Notification> notifications;
        try {
            notifications = notificationRepository.findAll().stream()
                    .filter(notification -> notification.getUserId().equals(userId))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error occurred while fetching notifications for user ID: {}", userId);
            throw new NotificationServiceException("Failed to fetch notifications");
        }

        return notifications;
    }

    @Override
    public void sendNotificationsToAllUsers(String subject, String message) {
        List<String> emails = userClient.getAllUserEmails();
        for (String email : emails) {
            emailNotificationService.sendEmail(email, subject, message);
        }
    }

}
