package com.ghulam.notificationservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "account-activated", groupId = "notification-group")
    public void handleCustomerCreated() {
        // Simulate sending notification
        log.info("Received Customer Created");
    }
}
