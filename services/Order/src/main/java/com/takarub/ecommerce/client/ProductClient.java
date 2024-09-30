package com.takarub.ecommerce.client;

import com.takarub.ecommerce.client.dto.PurchaseResponse;
import com.takarub.ecommerce.exception.BusinessException;
import com.takarub.ecommerce.product.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestsBody ,String token) {

       HttpHeaders headers = new HttpHeaders();
       headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
       // here id need to pass toke or any thing her you can do it
        headers.set(HttpHeaders.AUTHORIZATION, token);

       HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestsBody, headers);

       ParameterizedTypeReference<List<PurchaseResponse>> responseType =
               new ParameterizedTypeReference<>() {};

        ResponseEntity<List<PurchaseResponse>> responseEntity =
                restTemplate.exchange(productUrl + "/purchase",
                        POST,
                        requestEntity,
                        responseType
                );
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException
                    ("An error occurred while trying to retrieve the list of purchases: " + responseEntity.getStatusCode());

        }
        return responseEntity.getBody();
    }

//    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//
//        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
//        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>() {
//        };
//        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
//                productUrl + "/purchase",
//                POST,
//                requestEntity,
//                responseType
//        );
//
//        if (responseEntity.getStatusCode().isError()) {
//            throw new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
//        }
//        return  responseEntity.getBody();
//    }


}
