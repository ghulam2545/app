package com.ghulam.customerservice.kafka;

import com.ghulam.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.support.jsonsupport.JsonSupport;

@Service
public class CustomerProducer {

    @Qualifier("jsonKafkaTemplate")
    private final KafkaTemplate<String, Object> kafkaObjectTemplate;

    @Qualifier("stringKafkaTemplate")
    private final KafkaTemplate<String, String> kafkaStringTemplate;

    public CustomerProducer(KafkaTemplate<String, Object> kafkaObjectTemplate, KafkaTemplate<String, String> kafkaStringTemplate) {
        this.kafkaObjectTemplate = kafkaObjectTemplate;
        this.kafkaStringTemplate = kafkaStringTemplate;
    }

//    public void accountActivated(Customer customer) {
//        String TOPIC = "account-activated";
//        kafkaObjectTemplate.send(TOPIC, customer);
//    }

    public void message(Customer customer) {
        String TOPIC = "greeting-message";
        kafkaStringTemplate.send(TOPIC, JsonSupport.writeValueAsString(customer));
    }
}
