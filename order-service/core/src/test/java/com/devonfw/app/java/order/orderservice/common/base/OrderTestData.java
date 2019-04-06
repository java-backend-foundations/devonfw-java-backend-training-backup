package com.devonfw.app.java.order.orderservice.common.base;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;

import java.time.LocalDate;

public interface OrderTestData {
  LocalDate CREATION_DATE = LocalDate.of(2019, 03, 15);

  OrderEtoBuilder ORDER_NEW = OrderEtoBuilder.anOrderEto()
      .withCreationDate(CREATION_DATE)
      .withStatus(OrderStatus.NEW);

  OrderEtoBuilder ORDER_PAID = OrderEtoBuilder.anOrderEto()
      .withCreationDate(CREATION_DATE)
      .withStatus(OrderStatus.PAID);
}
