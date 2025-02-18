package com.example.loan_api.controller;


import com.example.loan_api.model.Customer;
import com.example.loan_api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customerList")
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }
}
