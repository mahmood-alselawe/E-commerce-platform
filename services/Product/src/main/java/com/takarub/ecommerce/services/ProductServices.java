package com.takarub.ecommerce.services;

import com.takarub.ecommerce.dto.ProductPurchaseRequest;
import com.takarub.ecommerce.dto.ProductRequest;
import com.takarub.ecommerce.dto.ProductResponse;
import com.takarub.ecommerce.dto.PurchaseProductsResponse;
import com.takarub.ecommerce.handler.ProductPurchaseException;
import com.takarub.ecommerce.mapper.ProductMapper;
import com.takarub.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServices {

    private final ProductRepository productRepository;

    private final ProductMapper mapper;


    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return productRepository.save(product).getId();
    }





    public List<PurchaseProductsResponse> purchaseProduct(
            List<ProductPurchaseRequest> request) {
        // when need i few checks before purchase products



        // two check id we have available Quantity for all products

        // extract the id
        List<Integer> productId = request.stream().map(ProductPurchaseRequest::productId)
                .toList();// return list of product Id

        // one check  if we have all  product (list<productId>==> List<Integer> ) on database

        var storedProducts = productRepository.findAllByIdInOrderById(productId);

        // this mean is customer buy 5 products
        // and when i query about this product in database i only get 4
        // in this case we need to block Request and return exception
        if (productId.size() != storedProducts.size()){
            throw new ProductPurchaseException("one or more products dose not exits");

        }
        // so here we sure that all products that user want it is available in database to
        // purchase

//        var storedRequest=

        List<ProductPurchaseRequest> storeRequest= request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

         var PurchaseProducts = new ArrayList<PurchaseProductsResponse>();

         for (int i = 0 ; i < storedProducts.size() ; i ++){
             var product = storedProducts.get(i);
             var productRequest = storeRequest.get(i);

             if (product.getAvailableQuantity() < productRequest.quantity()){
                 throw new ProductPurchaseException(" insufficient stock quantity for product with id:: " + productRequest.productId());
             }

             var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();

             product.setAvailableQuantity(newAvailableQuantity);

             productRepository.save(product);
             PurchaseProducts.add(mapper.toPurchaseProductsResponse(product,productRequest.quantity()));
         }

        return PurchaseProducts;
    }



    public ProductResponse findById(Integer productId) {

        return productRepository.findById(productId).map(mapper::toProductResponse)
                .orElseThrow(()->
                        new EntityNotFoundException("product not found with Id:: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
