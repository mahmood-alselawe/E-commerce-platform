package com.takarub.ecommerce.controller;

import com.takarub.ecommerce.dto.CustomerRequest;
import com.takarub.ecommerce.dto.CustomerResponse;
import com.takarub.ecommerce.model.Customer;
import com.takarub.ecommerce.services.CustomerServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerServices services;


    // first api create customer

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(services.createCustomer(request));
    }

    // to update the customer
    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request) {
        services.updateCustomer(request);
        return ResponseEntity.accepted().build();

    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(services.findAll());
    }

    @GetMapping("/exist/{customer-id}")
    public ResponseEntity<Boolean  > existById(@PathVariable("customer-id") String customerId) {
        return  ResponseEntity.ok(services.exist(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customer-id") String customerId) {

        return  ResponseEntity.ok(services.findById(customerId));

    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        services.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }


}
