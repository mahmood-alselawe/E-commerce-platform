package com.takarub.ecommerce.service;

import com.takarub.ecommerce.dto.PaymentRequest;
import com.takarub.ecommerce.kafka.NotificationProducer;
import com.takarub.ecommerce.kafka.dto.PaymentNotificationRequest;
import com.takarub.ecommerce.mapper.PaymentMapper;
import com.takarub.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    private final PaymentMapper mapper;

    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));

        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstname(),
                request.customer().lastname(),
                request.customer().email()
        ));
        return payment.getId();
    }
}
