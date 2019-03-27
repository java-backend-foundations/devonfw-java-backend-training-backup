package com.devonfw.app.java.order.orderservice.logic.base.usecase;

import javax.inject.Inject;

import com.devonfw.app.java.order.general.logic.base.AbstractUc;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.CustomerRepository;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.ItemRepository;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.OrderRepository;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;

public class AbstractOrderCtoUc extends AbstractUc {

	@Inject
	private OrderRepository orderRepository;

	@Inject
	private ItemRepository itemRepository;

	@Inject
	private CustomerRepository customerRepository;

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public ItemRepository getItemRepository() {
		return itemRepository;
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}


}
