package com.takarub.ecommerce.mapper;

import com.takarub.ecommerce.dto.OrderLineRequest;
import com.takarub.ecommerce.dto.OrderLineResponse;
import com.takarub.ecommerce.model.Order;
import com.takarub.ecommerce.orderline.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {

        return OrderLine
                .builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}

