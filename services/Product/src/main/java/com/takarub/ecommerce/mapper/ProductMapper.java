package com.takarub.ecommerce.mapper;

import com.takarub.ecommerce.category.Category;
import com.takarub.ecommerce.dto.ProductRequest;
import com.takarub.ecommerce.dto.ProductResponse;
import com.takarub.ecommerce.dto.PurchaseProductsResponse;
import com.takarub.ecommerce.model.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Products toProduct(ProductRequest request) {
        return Products
                .builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(
                        Category.
                                builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();

    }

    public ProductResponse toProductResponse(Products products) {
        return new ProductResponse(
                products.getId(),
                products.getName(),
                products.getDescription(),
                products.getAvailableQuantity(),
                products.getPrice(),
                products.getCategory().getId(),
                products.getCategory().getName(),
                products.getCategory().getDescription()
        );
    }

    public PurchaseProductsResponse toPurchaseProductsResponse(Products product, double quantity) {
        return new PurchaseProductsResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity

        );
    }
}
