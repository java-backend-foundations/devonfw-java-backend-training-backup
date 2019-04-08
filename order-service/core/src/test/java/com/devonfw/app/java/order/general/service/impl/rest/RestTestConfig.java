package com.devonfw.app.java.order.general.service.impl.rest;

import com.devonfw.app.java.order.general.base.RestTestClientBuilder;
import com.devonfw.app.java.order.general.common.base.test.DbTestHelper;
import com.devonfw.module.basic.common.api.config.SpringProfileConstants;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * This configuration class provides {@code @Bean} annotated methods. It is applied to a test class by using the
 * following class annotation: {@code @SpringApplicationConfiguration(classes = RestaurantTestConfig.class)}. Hence,
 * beans provided by {@code @Bean} annotated methods will not be available outside the test configuration. <br/>
 * <br/>
 */
@Configuration
@Profile(SpringProfileConstants.JUNIT)
public class RestTestConfig {

  /**
   * The constructor.
   */
  public RestTestConfig() {
    super();
  }

  /**
   * @return an instance of type {@code RestTestClientBuilder}
   */
  @Bean
  public RestTestClientBuilder restTestClientBuilder() {

    return new RestTestClientBuilder();
  }


  /**
   * @param flyway an instance of type {@link Flyway}.
   * @return an instance of type {@link DbTestHelper}.
   */
  @Bean
  public DbTestHelper dbTestHelper(Flyway flyway) {

    return new DbTestHelper(flyway);

  }

}
