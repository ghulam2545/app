package com.ghulam.notificationservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.ghulam.notificationservice.service.NotificationService;
import com.ghulam.notificationservice.utils.Constants;
import com.support.jsonsupport.JsonSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    private static final String CUSTOMER_EMAIL = "ghulam2545@gmail.com";
    private static final String CUSTOMER_NAME = "Ghulam Mustafa";

    @KafkaListener(topics = Constants.CUSTOMER_CREATED_TOPIC, groupId = "notification-group", containerFactory = "stringKafkaListenerFactory")
    public void handleCustomerCreated(String s) {
        JsonNode customerJson = JsonSupport.readTree(s);
        log.info("handleCustomerCreated {}", customerJson);
        notificationService.sendCustomerAcknowledgement(CUSTOMER_EMAIL, CUSTOMER_NAME);
    }

    @KafkaListener(topics = Constants.REPORT_GENERATION_TOPIC, groupId = "notification-group", containerFactory = "stringKafkaListenerFactory")
    public void handleReportGeneration(String s) {
        // Simulate sending notification
        log.info("handleReportGeneration {}", s);
    }
}
