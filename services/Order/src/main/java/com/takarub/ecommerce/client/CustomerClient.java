package com.takarub.ecommerce.client;

import com.takarub.ecommerce.client.confi.FeignConfig;
import com.takarub.ecommerce.client.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(
        name = "CUSTOMER-SERVICES",
        url = "${application.config.customer-url}"

)
public interface CustomerClient {

    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId , @RequestHeader("Authorization") String token);
}
