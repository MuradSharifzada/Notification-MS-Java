package com.example.notification_managements.mapper;


import com.example.notification_managements.dto.NotificationRequest;
import com.example.notification_managements.dto.NotificationResponse;
import com.example.notification_managements.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification requestToEntity(NotificationRequest request);

    NotificationResponse entityToResponse(Notification notification);
}
