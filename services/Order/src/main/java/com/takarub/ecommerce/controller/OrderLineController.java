package com.takarub.ecommerce.controller;

import com.takarub.ecommerce.dto.OrderLineResponse;
import com.takarub.ecommerce.orderline.OrderLine;
import com.takarub.ecommerce.services.OrderLineServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineServices orderLineServices;


    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderID(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(orderLineServices.findByOrderID(orderId));
    }


}
