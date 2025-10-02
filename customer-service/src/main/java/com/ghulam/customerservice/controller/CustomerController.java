package com.ghulam.customerservice.controller;

import com.ghulam.customerservice.dto.CustomerDto;
import com.ghulam.customerservice.kafka.CustomerProducer;
import com.ghulam.customerservice.model.Customer;
import com.ghulam.customerservice.repo.CustomerRepo;
import com.ghulam.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepo customerRepo;
    private final CustomerProducer customerProducer;

    CustomerController(CustomerService customerService, CustomerRepo customerRepo, CustomerProducer customerProducer) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
        this.customerProducer = customerProducer;
    }

    @GetMapping(path = "/customers")
    public ResponseEntity<Object> customerList() {
        return ResponseEntity.ok(customerRepo.findAll());
    }

    @PostMapping(path = "/customer")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto request) {
        Customer customer = customerService.createCustomer(request);
        customerProducer.message(customer);
        return ResponseEntity.ok(customer);
    }
}
