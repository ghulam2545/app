package com.ghulam.reportservice.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.ghulam.reportservice.service.ReportService;
import com.ghulam.reportservice.utils.Constants;
import com.support.jsonsupport.JsonSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ReportConsumer {

    private final ReportService reportService;

    public ReportConsumer(ReportService reportService) {
        this.reportService = reportService;
    }

    @KafkaListener(topics = Constants.CUSTOMER_CREATED_TOPIC, groupId = "report-group", containerFactory = "stringKafkaListenerFactory")
    public void handleReportGeneration(String s) {
        JsonNode customerJson = JsonSupport.readTree(s);
        log.info("handleReportGeneration {}", customerJson);

        Map<String, String> map = new HashMap<>();
        map.put("customerId", customerJson.path("customerId").asText());
        map.put("firstName", customerJson.path("firstName").asText());
        map.put("lastName", customerJson.path("lastName").asText());

        reportService.saveReport(map);
    }
}
