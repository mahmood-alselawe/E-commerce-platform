package com.takarub.ecommerce.mapper;

import com.takarub.ecommerce.dto.CustomerRequest;
import com.takarub.ecommerce.dto.CustomerResponse;
import com.takarub.ecommerce.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomerMapper {


    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return Customer
                .builder()
                .id(request.Id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .address(request.address())
                .email(request.email())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
