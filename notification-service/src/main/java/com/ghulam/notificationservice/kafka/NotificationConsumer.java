package com.ghulam.notificationservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "customer-created", groupId = "notification-group", containerFactory = "stringKafkaListenerFactory")
    public void handleCustomerCreated(String s) {
        // Simulate sending notification
        log.info("handleCustomerCreated {}", s);
    }
}
