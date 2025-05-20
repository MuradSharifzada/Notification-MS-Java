package com.example.notification_managements.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotificationNotFoundException extends RuntimeException {
    private String message;

    public NotificationNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
