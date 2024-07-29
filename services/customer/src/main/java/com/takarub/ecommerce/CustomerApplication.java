package com.takarub.ecommerce;

import com.takarub.ecommerce.mapper.CustomerMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.takarub.ecommerce", "mapper"})
// this not required
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

//	@Bean
//	public CustomerMapper customerMapper() {
//		return new CustomerMapper();
//	}

}
