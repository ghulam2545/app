package com.ghulam.customerservice.controller;

import com.ghulam.customerservice.dto.CustomerDto;
import com.ghulam.customerservice.repo.CustomerRepo;
import com.ghulam.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepo customerRepo;

    CustomerController(CustomerService customerService, CustomerRepo customerRepo) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
    }

    @GetMapping(path = "/customers")
    public ResponseEntity<Object> customerList() {
        return ResponseEntity.ok(customerRepo.findAll());
    }

    @PostMapping(path = "/customer")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}
