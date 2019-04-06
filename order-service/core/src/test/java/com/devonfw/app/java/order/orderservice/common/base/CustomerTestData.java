package com.devonfw.app.java.order.orderservice.common.base;

/**
 * The interface Customer test data.
 */
public interface CustomerTestData {

  /**
   * The constant SOME_CUSTOMER.
   */
  CustomerEtoBuilder SOME_CUSTOMER = CustomerEtoBuilder.aCustomerEto();

  /**
   * The constant MICKEY_MOUSE.
   */
  CustomerEtoBuilder MICKEY_MOUSE = CustomerEtoBuilder.aCustomerEto()
      .withFirstname("Mickey")
      .withLastname("Mouse");

}
