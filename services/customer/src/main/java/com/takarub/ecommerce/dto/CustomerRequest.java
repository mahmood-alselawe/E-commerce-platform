package com.takarub.ecommerce.dto;

import com.takarub.ecommerce.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String Id,
        @NotNull(message = "Customer first name is Required")
        String firstName,
        @NotNull(message = "Customer last name is Required")
        String lastName,
        @NotNull(message = "Customer email is Required")
        @Email(message = "customer email is not valid email address")
        String email,
        Address address
) {
}
