package com.takarub.ecommerce.model;

import com.takarub.ecommerce.kafka.dto.PaymentNotificationRequest;
import com.takarub.ecommerce.kafka.order.OrderConfirmation;
import com.takarub.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDate notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentNotificationRequest paymentConfirmation;


}
