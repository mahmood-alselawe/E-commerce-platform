package com.takarub.ecommerce.dto;

import java.math.BigDecimal;

public record PurchaseProductsResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
