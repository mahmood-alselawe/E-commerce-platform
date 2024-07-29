package com.takarub.ecommerce.kafka;

import com.takarub.ecommerce.kafka.dto.PaymentNotificationRequest;
import com.takarub.ecommerce.kafka.order.OrderConfirmation;
import com.takarub.ecommerce.kafka.payment.PaymentConfirmation;
import com.takarub.ecommerce.model.Notification;
import com.takarub.ecommerce.model.NotificationType;
import com.takarub.ecommerce.repository.NotificationRepository;
import com.takarub.ecommerce.services.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.takarub.ecommerce.model.NotificationType.ORDER_CONFIRMATION;
import static com.takarub.ecommerce.model.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j

public class NotificationConsumer {

    private final NotificationRepository repository;

    private final EmailService emailService;

    // private final EmailService service;

    // this object should be same object from producer
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentNotificationRequest paymentNotificationRequest) throws MessagingException {
        log.info("Payment confirmation: {}", paymentNotificationRequest);
        repository.save(
                Notification
                        .builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDate.now())
                        .paymentConfirmation(paymentNotificationRequest)
                        .build()
        );

        // todo send email
        var customerName = paymentNotificationRequest.customerFirstName() + " " + paymentNotificationRequest.customerLastName();
        emailService.sendEmailSuccessEmail(
                paymentNotificationRequest.customerEmail(),
                customerName,
                paymentNotificationRequest.amount(),
                paymentNotificationRequest.orderReference()
        );


    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
       log.info("Order confirmation: {}", orderConfirmation);
        repository.save(
                Notification
                        .builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDate.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        // todo send email
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmation(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );


    }

}
