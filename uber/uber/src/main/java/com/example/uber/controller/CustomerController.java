package com.example.uber.controller;

import com.example.uber.dto.request.CustomerRequest;
import com.example.uber.entity.Customer;
import com.example.uber.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/uber/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public String createCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping("/")
    public Optional<Customer> customerGetById(@RequestParam Integer customerId){
        return customerService.findById(customerId);
    }
}
