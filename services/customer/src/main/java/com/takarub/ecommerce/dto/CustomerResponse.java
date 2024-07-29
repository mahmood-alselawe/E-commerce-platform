package com.takarub.ecommerce.dto;

import com.takarub.ecommerce.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        String Id,

        String firstName,

        String lastName,

        String email,

        Address address
) {


}
