package com.takarub.ecommerce.client.confi;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor(FeignClientInterceptor feignClientInterceptor) {
        return feignClientInterceptor;
    }
}
