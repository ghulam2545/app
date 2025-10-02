package com.ghulam.notificationservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.ghulam.notificationservice.service.NotificationService;
import com.ghulam.notificationservice.utils.Constants;
import com.support.jsonsupport.JsonSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    @KafkaListener(topics = Constants.REPORT_GENERATION_TOPIC, groupId = "report-group", containerFactory = "stringKafkaListenerFactory")
    public void handleReportGeneration(String s) {
        log.info("handleReportGeneration {}", s);

        String filename = "reports/report.pdf";

        byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file = new File(filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        notificationService.sendCustomerReport(CUSTOMER_EMAIL, CUSTOMER_NAME, file);
    }
}
