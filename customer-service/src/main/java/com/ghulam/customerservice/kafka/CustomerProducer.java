package com.ghulam.customerservice.kafka;

import com.ghulam.customerservice.model.Customer;
import com.support.jsonsupport.JsonSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CustomerProducer {

    @Qualifier("jsonKafkaTemplate")
    private final KafkaTemplate<String, Object> kafkaObjectTemplate; // TODO: unused as of now

    @Qualifier("stringKafkaTemplate")
    private final KafkaTemplate<String, String> kafkaStringTemplate;

    private static final String TOPIC = "customer-created";
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProducer.class);


    public CustomerProducer(KafkaTemplate<String, Object> kafkaObjectTemplate, KafkaTemplate<String, String> kafkaStringTemplate) {
        this.kafkaObjectTemplate = kafkaObjectTemplate;
        this.kafkaStringTemplate = kafkaStringTemplate;
    }

    public void sendAsync(Customer customer) {
        String customerJson = JsonSupport.writeValueAsString(customer);

        CompletableFuture<SendResult<String, String>> future =
                kafkaStringTemplate.send(TOPIC, customerJson);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.info("Sent message='{}' to topic='{}' with offset={}",
                        customerJson,
                        TOPIC,
                        result.getRecordMetadata().offset());
            } else {
                LOGGER.error("Unable to send message='{}' to topic='{}' due to: {}",
                        customerJson,
                        TOPIC,
                        ex.getMessage());
            }
        });
    }
}
