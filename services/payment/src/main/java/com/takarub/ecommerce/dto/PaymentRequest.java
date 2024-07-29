package com.takarub.ecommerce.dto;

import com.takarub.ecommerce.model.Customer;
import com.takarub.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
