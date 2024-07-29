package com.takarub.ecommerce.services;

import com.takarub.ecommerce.dto.CustomerRequest;
import com.takarub.ecommerce.dto.CustomerResponse;
import com.takarub.ecommerce.exception.CustomNotFoundException;
import com.takarub.ecommerce.model.Customer;
import com.takarub.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import com.takarub.ecommerce.mapper.CustomerMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServices {

    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
       var customer = repository.findById(request.Id())
               .orElseThrow(()-> new CustomNotFoundException(
                       String.format("Customer with id '%s' not found", request.Id())
               ));
       // this methods for checks request  is isNotBlank
       mergerCustomer(customer,request);
       repository.save(customer);

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(customer.getFirstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(customer.getLastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(customer.getEmail())) {
            customer.setEmail(request.email());
        }
        if (customer.getAddress() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean exist(String customerId) {

        return repository.findById(customerId)
                .isPresent(); // return true if user is exist else return false

    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomNotFoundException(customerId));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }


}
