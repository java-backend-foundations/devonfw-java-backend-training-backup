package com.devonfw.app.java.order.orderservice.common.base;

import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;

/**
 * The type Customer eto builder.
 */
public final class CustomerEtoBuilder {
  private String firstname;
  private String lastname;
  private Long id;
  private int modificationCounter;

  private CustomerEtoBuilder() {
  }

  /**
   * A customer eto customer eto builder.
   *
   * @return the customer eto builder
   */
  public static CustomerEtoBuilder aCustomerEto() {
    return new CustomerEtoBuilder();
  }

  /**
   * With firstname customer eto builder.
   *
   * @param firstname the firstname
   * @return the customer eto builder
   */
  public CustomerEtoBuilder withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * With lastname customer eto builder.
   *
   * @param lastname the lastname
   * @return the customer eto builder
   */
  public CustomerEtoBuilder withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * With id customer eto builder.
   *
   * @param id the id
   * @return the customer eto builder
   */
  public CustomerEtoBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  /**
   * With modification counter customer eto builder.
   *
   * @param modificationCounter the modification counter
   * @return the customer eto builder
   */
  public CustomerEtoBuilder withModificationCounter(int modificationCounter) {
    this.modificationCounter = modificationCounter;
    return this;
  }

  /**
   * Build customer eto.
   *
   * @return the customer eto
   */
  public CustomerEto build() {
    CustomerEto customerEto = new CustomerEto();
    customerEto.setFirstname(firstname);
    customerEto.setLastname(lastname);
    customerEto.setId(id);
    customerEto.setModificationCounter(modificationCounter);
    return customerEto;
  }
}
