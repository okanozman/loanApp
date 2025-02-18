package com.example.loan_api.repository;

import com.example.loan_api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository  extends JpaRepository<Customer,Long> { }
