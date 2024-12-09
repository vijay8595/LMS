package com.example.uber.service;


import com.example.uber.dto.request.CustomerRequest;
import com.example.uber.entity.Customer;


import com.example.uber.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public String createCustomer(CustomerRequest customerRequest){
        if(customerRequest == null){
            return "Invalid customer request";
        }
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .build();

        customerRepository.save(customer);
        return "Customer created successfully. Login can now be created.";
    }

    public Optional<Customer> findById(Integer id){
        return customerRepository.findById(id);
    }
}
