package com.takarub.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

        Integer id,
        @NotNull(message = "product name is required")
        String name,
        @NotNull(message = "product description is required")
        String description,
        @Positive(message = "available Quantity should be Positive ")
        double availableQuantity,
        @Positive(message = "price should be Positive ")
        BigDecimal price,
        @NotNull(message = "categoryId description is required")
        Integer categoryId

) {
}
