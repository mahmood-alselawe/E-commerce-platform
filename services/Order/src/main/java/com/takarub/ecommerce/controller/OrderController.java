package com.takarub.ecommerce.controller;

import com.takarub.ecommerce.dto.OrderRequest;
import com.takarub.ecommerce.dto.OrderResponse;
import com.takarub.ecommerce.services.OrderServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(orderServices.createOrder(request));
    }


    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderServices.findAll());
    }


    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(orderServices.findById(orderId));

    }
}
