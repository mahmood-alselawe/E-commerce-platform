package com.takarub.ecommerce.services;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html","Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html","Order successfully processed")
    ;
    @Getter
    private final String templateName;

    @Getter
    private final String subject;

    EmailTemplates(String templateName, String subject) {
        this.templateName = templateName;
        this.subject = subject;
    }
}
