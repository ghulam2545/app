package com.ghulam.customerservice.service;

import com.ghulam.customerservice.dto.CustomerDto;
import com.ghulam.customerservice.kafka.CustomerProducer;
import com.ghulam.customerservice.model.Customer;
import com.ghulam.customerservice.repo.CustomerRepo;
import com.ghulam.customerservice.utils.CommonUtils;
import com.ghulam.customerservice.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerProducer customerProducer;

    public CustomerService(CustomerRepo customerRepo, CustomerProducer customerProducer) {
        this.customerRepo = customerRepo;
        this.customerProducer = customerProducer;
    }

    public Customer createCustomer(CustomerDto customer) {
        var newCustomer = new Customer();
        newCustomer.setCustomerId(CommonUtils.getUUID());
        newCustomer.setFirstName(customer.firstName());
        newCustomer.setLastName(customer.lastName());
        Customer saved = customerRepo.save(newCustomer);
        customerProducer.sendAsync(saved, Constants.CUSTOMER_CREATED_TOPIC); // publish initial mail send
        customerProducer.sendAsync(saved, Constants.REPORT_GENERATION_TOPIC); // publish report generation start
        return saved;
    }

    public Collection<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }
}
