package com.ghulam.reportservice.kafka;

import com.support.jsonsupport.JsonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportProducer {

    private final KafkaTemplate<String, String> kafkaStringTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportProducer.class);

    public ReportProducer(KafkaTemplate<String, String> kafkaStringTemplate) {
        this.kafkaStringTemplate = kafkaStringTemplate;
    }

    @Async
    public void sendAsync(Map<String, String> customer, String topic) {
        String customerJson = JsonSupport.writeValueAsString(customer);

        CompletableFuture<SendResult<String, String>> future =
                kafkaStringTemplate.send(topic, customerJson);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Sent message='{}' to topic='{}' with offset={}",
                        customerJson,
                        topic,
                        result.getRecordMetadata().offset());
            } else {
                LOGGER.error("Unable to send message='{}' to topic='{}' due to: {}",
                        customerJson,
                        topic,
                        ex.getMessage());
            }
        });
    }
}
