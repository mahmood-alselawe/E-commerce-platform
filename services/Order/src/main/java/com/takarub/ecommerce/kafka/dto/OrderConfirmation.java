package com.takarub.ecommerce.kafka.dto;

import com.takarub.ecommerce.client.dto.CustomerResponse;
import com.takarub.ecommerce.client.dto.PurchaseResponse;
import com.takarub.ecommerce.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
