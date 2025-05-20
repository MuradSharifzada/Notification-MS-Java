package com.example.notification_managements.dto;

import lombok.Data;

@Data
public class NotificationResponse {
    private String status;
    private String recipient;
    private String subject;
    private String message;

}
