package com.ghulam.customerservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CustomerProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void accountActivated() {
        String TOPIC = "account-activated";
        kafkaTemplate.send(TOPIC, "customer account activated");
    }
}
