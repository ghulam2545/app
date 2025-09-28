package com.ghulam.customerservice.controller;

import com.ghulam.customerservice.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @PostMapping(path = "/customers")
    public ResponseEntity<Object> customerList() {
        return ResponseEntity.ok(customerRepo.findAll());
    }
}
