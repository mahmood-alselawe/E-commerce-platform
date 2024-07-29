package com.takarub.ecommerce.controller;

import com.takarub.ecommerce.dto.ProductPurchaseRequest;
import com.takarub.ecommerce.dto.ProductRequest;
import com.takarub.ecommerce.dto.ProductResponse;
import com.takarub.ecommerce.dto.PurchaseProductsResponse;
import com.takarub.ecommerce.services.ProductServices;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServices services;


    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(services.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<PurchaseProductsResponse>> purchaseProduct(
            @RequestBody @Valid List<ProductPurchaseRequest> request) {
        return ResponseEntity.ok(services.purchaseProduct(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId){
        return ResponseEntity.ok(services.findById(productId));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(services.findAll());
    }








}
