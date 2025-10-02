package com.ghulam.notificationservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

//    @KafkaListener(topics = "account-activated", groupId = "notification-group", containerFactory = "jsonKafkaListenerFactory")
//    public void handleCustomerCreated(Customer customer) {
//        // Simulate sending notification
//        log.info("Received Customer Created {}", customer);
//    }

    @KafkaListener(topics = "greeting-message", groupId = "notification-group", containerFactory = "stringKafkaListenerFactory")
    public void handleGreetingMessage(String s) {
        // Simulate sending notification
        log.info("Received Greeting Message {}", s);
    }
}
