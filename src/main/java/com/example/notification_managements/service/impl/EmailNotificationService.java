package com.example.notification_managements.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {
    private static final Logger log = LoggerFactory.getLogger(EmailNotificationService.class);
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("springnotificationservice@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        try {
            log.info("try to sending email");
            mailSender.send(message);
            log.info("Email sent successfully {}", to);
        } catch (Exception e) {
            log.error("Failed to send email");
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
