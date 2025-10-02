package com.ghulam.customerservice.controller;

import com.ghulam.customerservice.dto.CustomerDto;
import com.ghulam.customerservice.model.Customer;
import com.ghulam.customerservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

    private final CustomerService customerService;

    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers")
    public ResponseEntity<Object> customerList() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping(path = "/customer")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto request) {
        Customer customer = customerService.createCustomer(request);
        return ResponseEntity.ok(customer);
    }
}
