package com.ghulam.customerservice.service;

import com.ghulam.customerservice.dto.CustomerDto;
import com.ghulam.customerservice.model.Customer;
import com.ghulam.customerservice.repo.CustomerRepo;
import com.ghulam.customerservice.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer createCustomer(CustomerDto customer) {
        var newCustomer = new Customer();
        newCustomer.setCustomerId(CommonUtils.getUUID());
        newCustomer.setFirstName(customer.firstName());
        newCustomer.setLastName(customer.lastName());
        return customerRepo.save(newCustomer);
    }

    public Collection<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }
}
