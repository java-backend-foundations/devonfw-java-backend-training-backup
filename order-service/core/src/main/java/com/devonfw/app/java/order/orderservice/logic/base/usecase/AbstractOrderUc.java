package com.devonfw.app.java.order.orderservice.logic.base.usecase;

import javax.inject.Inject;

import com.devonfw.app.java.order.general.logic.base.AbstractUc;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.OrderRepository;

/**
 * Abstract use case for Orders, which provides access to the commonly necessary
 * data access objects.
 */
public class AbstractOrderUc extends AbstractUc {

	/** @see #getOrderRepository() */
	@Inject
	private OrderRepository orderRepository;

	/**
	 * Returns the field 'orderRepository'.
	 * 
	 * @return the {@link OrderRepository} instance.
	 */
	public OrderRepository getOrderRepository() {

		return this.orderRepository;
	}

}
