package com.devonfw.app.java.order.orderservice.service.impl.rest;

import java.time.LocalDate;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.service.api.rest.OrderserviceRestService;

/**
 * The service implementation for REST calls in order to execute the logic of
 * component {@link Orderservice}.
 */
@Named("OrderserviceRestService")
public class OrderserviceRestServiceImpl implements OrderserviceRestService {

	@Inject
	private Orderservice orderservice;

	@Override
	public void deleteCustomer(long id) {
		this.orderservice.deleteCustomer(id);
	}

	@Override
	public Page<ItemEto> findItemsWithNameLikeOrdered(String name) {
		return this.orderservice.findItemsWithNameLikeOrdered(name);
	}

	@Override
	public Set<ItemEto> findItemByName(String name) {
		return this.orderservice.findByName(name);
	}

	@Override
	public void raiseItemPriceByOne(String name) {
		this.orderservice.raiseItemPriceByOne(name);
	}

	@Override
	public Set<OrderEto> findOrdersByCreationDateAndStatus(LocalDate date, OrderStatus status) {
		return this.orderservice.findOrdersByCreationDateAndStatus(date, status);
	}

	@Override
	public OrderCto saveOrder(OrderCto order) {
		return this.orderservice.saveOrder(order);
	}



}
