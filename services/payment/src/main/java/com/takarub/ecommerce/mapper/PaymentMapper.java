package com.takarub.ecommerce.mapper;

import com.takarub.ecommerce.dto.PaymentRequest;
import com.takarub.ecommerce.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment
                .builder()
                .id(request.id())
                .orderId(request.orderId())
                .amount(request.amount())
                .PaymentMethod(request.paymentMethod())
                .build();
    }
}
