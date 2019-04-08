package com.devonfw.app.java.order.general.service.impl.rest;

import java.util.Arrays;
import java.util.HashSet;

import com.devonfw.app.java.order.orderservice.common.base.CustomerTestData;
import com.devonfw.app.java.order.orderservice.common.base.ItemTestData;
import com.devonfw.app.java.order.orderservice.common.base.OrderTestData;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;

/**
 * The type Order service rest test helper.
 */
public class OrderServiceRestTestHelper {

	/**
	 * Create dummy order cto order cto.
	 *
	 * @return the order cto
	 */
	public OrderCto createDummyOrderCTO() {
		OrderCto orderCto = new OrderCto();
		orderCto.setOrder(OrderTestData.ORDER_NEW.build());
		orderCto.setOwner(CustomerTestData.MICKEY_MOUSE.build());
		orderCto.setOrderPositions(
				new HashSet<>(Arrays.asList(ItemTestData.CORN.build(), ItemTestData.CHEESE.build())));
		return orderCto;
	}
}
