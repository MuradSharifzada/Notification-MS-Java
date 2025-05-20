package com.example.notification_managements.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NotificationServiceException extends RuntimeException {

    private final String message;

    public NotificationServiceException(String message) {
        super(message);
        this.message = message;
    }
}
