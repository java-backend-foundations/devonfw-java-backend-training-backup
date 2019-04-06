package com.devonfw.app.java.order.orderservice.common.base;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;

import java.time.LocalDate;

/**
 * The type Order eto builder.
 */
public final class OrderEtoBuilder {
  private Double price;
  private OrderStatus status;
  private LocalDate creationDate;
  private Long ownerId;
  private Long id;
  private int modificationCounter;

  private OrderEtoBuilder() {
  }

  /**
   * An order eto order eto builder.
   *
   * @return the order eto builder
   */
  public static OrderEtoBuilder anOrderEto() {
    return new OrderEtoBuilder();
  }

  /**
   * With price order eto builder.
   *
   * @param price the price
   * @return the order eto builder
   */
  public OrderEtoBuilder withPrice(Double price) {
    this.price = price;
    return this;
  }

  /**
   * With status order eto builder.
   *
   * @param status the status
   * @return the order eto builder
   */
  public OrderEtoBuilder withStatus(OrderStatus status) {
    this.status = status;
    return this;
  }

  /**
   * With creation date order eto builder.
   *
   * @param creationDate the creation date
   * @return the order eto builder
   */
  public OrderEtoBuilder withCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * With owner id order eto builder.
   *
   * @param ownerId the owner id
   * @return the order eto builder
   */
  public OrderEtoBuilder withOwnerId(Long ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  /**
   * With id order eto builder.
   *
   * @param id the id
   * @return the order eto builder
   */
  public OrderEtoBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  /**
   * With modification counter order eto builder.
   *
   * @param modificationCounter the modification counter
   * @return the order eto builder
   */
  public OrderEtoBuilder withModificationCounter(int modificationCounter) {
    this.modificationCounter = modificationCounter;
    return this;
  }

  /**
   * Build order eto.
   *
   * @return the order eto
   */
  public OrderEto build() {
    OrderEto orderEto = new OrderEto();
    orderEto.setPrice(price);
    orderEto.setStatus(status);
    orderEto.setCreationDate(creationDate);
    orderEto.setOwnerId(ownerId);
    orderEto.setId(id);
    orderEto.setModificationCounter(modificationCounter);
    return orderEto;
  }
}
