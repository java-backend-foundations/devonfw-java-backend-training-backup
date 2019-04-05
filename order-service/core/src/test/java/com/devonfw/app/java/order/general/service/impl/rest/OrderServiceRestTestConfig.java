package com.devonfw.app.java.order.general.service.impl.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceRestTestConfig {

    @Bean
    public OrderServiceRestTestHelper orderServiceRestTestHelper() {
        OrderServiceRestTestHelper orderServiceRestTestHelper = new OrderServiceRestTestHelper();
        return orderServiceRestTestHelper;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
