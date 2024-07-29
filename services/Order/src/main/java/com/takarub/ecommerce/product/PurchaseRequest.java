package com.takarub.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "products is mandatory")
        Integer productId,
        @Positive(message = "quantity is mandatory")
        double quantity
) {
}
