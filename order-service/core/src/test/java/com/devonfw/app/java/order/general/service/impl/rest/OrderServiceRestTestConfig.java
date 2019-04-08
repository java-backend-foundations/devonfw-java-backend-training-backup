package com.devonfw.app.java.order.general.service.impl.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The type Order service rest test config.
 */
@Configuration
public class OrderServiceRestTestConfig {

  /**
   * Order service rest test helper order service rest test helper.
   *
   * @return the order service rest test helper
   */
  @Bean
  public OrderServiceRestTestHelper orderServiceRestTestHelper() {
    OrderServiceRestTestHelper orderServiceRestTestHelper = new OrderServiceRestTestHelper();
    return orderServiceRestTestHelper;
  }

  /**
   * Rest template rest template.
   *
   * @return the rest template
   */
  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate;
  }
}
