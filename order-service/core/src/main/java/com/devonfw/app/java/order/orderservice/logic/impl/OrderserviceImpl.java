package com.devonfw.app.java.order.orderservice.logic.impl;

import java.time.LocalDate;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.general.logic.base.AbstractComponentFacade;
import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageOrder;

import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;

/**
 * Implementation of component interface of orderservice
 */
@Named
public class OrderserviceImpl extends AbstractComponentFacade implements Orderservice {

	@Inject
	private UcFindOrder ucFindOrder;

	@Inject
	private UcManageOrder ucManageOrder;

	@Inject
	private UcFindCustomer ucFindCustomer;

	@Inject
	private UcManageCustomer ucManageCustomer;

	@Inject
	private UcFindItem ucFindItem;

	@Inject
	private UcManageItem ucManageItem;

	@Override
	public OrderEto findOrder(long id) {

		return ucFindOrder.findOrder(id);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_ORDER)
	public Page<OrderEto> findOrders(OrderSearchCriteriaTo criteria) {
		return this.ucFindOrder.findOrders(criteria);
	}

	@Override
	@RolesAllowed(PermissionConstants.SAVE_ORDER)
	public OrderEto saveOrder(OrderEto order) {

		return this.ucManageOrder.saveOrder(order);
	}

	@Override
	@RolesAllowed(PermissionConstants.DELETE_ORDER)
	public boolean deleteOrder(long id) {

		return this.ucManageOrder.deleteOrder(id);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_CUSTOMER)
	public CustomerEto findCustomer(long id) {

		return this.ucFindCustomer.findCustomer(id);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_CUSTOMER)
	public Page<CustomerEto> findCustomers(CustomerSearchCriteriaTo criteria) {
		return this.ucFindCustomer.findCustomers(criteria);
	}

	@Override
	@RolesAllowed(PermissionConstants.SAVE_CUSTOMER)
	public CustomerEto saveCustomer(CustomerEto customer) {

		return this.ucManageCustomer.saveCustomer(customer);
	}

	@Override
	@RolesAllowed(PermissionConstants.DELETE_CUSTOMER)
	public boolean deleteCustomer(long id) {

		return this.ucManageCustomer.deleteCustomer(id);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_ITEM)
	public ItemEto findItem(long id) {

		return this.ucFindItem.findItem(id);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_ITEM)
	public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {
		return this.ucFindItem.findItems(criteria);
	}

	@Override
	@RolesAllowed(PermissionConstants.SAVE_ITEM)
	public ItemEto saveItem(ItemEto item) {

		return this.ucManageItem.saveItem(item);
	}

	@Override
	@RolesAllowed(PermissionConstants.DELETE_ITEM)
	public boolean deleteItem(long id) {
		return this.ucManageItem.deleteItem(id);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_ITEM)
	public Page<ItemEto> findItemsWithNameLikeOrdered(String name){
		return ucFindItem.findItemsWithNameLikeOrdered(name);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_ITEM)
	public Set<ItemEto> findByName(String name) {
		return ucFindItem.findByName(name);
	}

	@Override
	@RolesAllowed(PermissionConstants.RAISE_ITEM_PRICE)
	public void raiseItemPrice(String name, Float price) {
		ucManageItem.raiseItemPrice(name, price);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_ORDER)
	public Set<OrderEto> findOrdersByCreationDateAndStatus(LocalDate creationDate, OrderStatus status) {
		return ucFindOrder.findOrdersByCreationDateAndStatus(creationDate, status);
	}

	@Override
	@RolesAllowed(PermissionConstants.FIND_ORDER)
	public OrderCto saveOrder(OrderCto order) {
		return ucManageOrder.saveOrder(order);
	}

}
