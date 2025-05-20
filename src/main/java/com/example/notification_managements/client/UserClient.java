package com.example.notification_managements.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "tutorial-service", url = "http://localhost:8080/api/clients")
public interface UserClient {

    @GetMapping("/emails")
    List<String> getAllUserEmails();

}
