package com.example.notification_managements.controller;

import com.example.notification_managements.dto.NotificationRequest;
import com.example.notification_managements.dto.NotificationResponse;
import com.example.notification_managements.model.Notification;
import com.example.notification_managements.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Tag(name = "Notification API", description = "Endpoints for managing notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notifications/send")
    @Operation(
            summary = "Send a notification",
            description = "Creates and sends a notification based on the provided request data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Notification sent successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NotificationResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data")
            }
    )
    public ResponseEntity<NotificationResponse> notifyUser(@RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.createNotification(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get notification by ID",
            description = "Retrieves a specific notification by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Notification found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notification.class))),
                    @ApiResponse(responseCode = "404", description = "Notification not found")
            }
    )
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        Notification notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Get notifications by user ID",
            description = "Fetches all notifications associated with a specific user ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of notifications for the user retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notification.class))),
                    @ApiResponse(responseCode = "404", description = "No notifications found for the user")
            }
    )
    public ResponseEntity<List<Notification>> getNotificationsByUserId(@PathVariable Long userId) {
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }
}
