package com.takarub.ecommerce.dto;

import com.takarub.ecommerce.model.PaymentMethod;
import com.takarub.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "order amount should by Positive")
        BigDecimal amount,
        @NotNull(message = "payment methods should not be null")
        PaymentMethod paymentMethod,

        @NotEmpty(message = "payment methods should be present")
        @NotBlank(message = "payment methods should be present")
        @NotNull(message = "payment methods should be present")
        String customerId,
        // this hold list of information about products
        // that we want to Purchase
        @NotEmpty(message = "you should at lest Purchase one products")
        List<PurchaseRequest> products

) {
}
