package com.example.notification_managements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificationManagementMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationManagementMsApplication.class, args);
    }



}
