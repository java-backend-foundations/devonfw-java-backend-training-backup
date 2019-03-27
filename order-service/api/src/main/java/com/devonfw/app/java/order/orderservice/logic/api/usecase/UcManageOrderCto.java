package com.devonfw.app.java.order.orderservice.logic.api.usecase;

import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;

public interface UcManageOrderCto {

	OrderCto saveOrder(OrderCto order);

	OrderCto findOrderCto(Long id);

}
