package com.devonfw.app.java.order.orderservice.logic.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.devonfw.app.java.order.general.logic.base.AbstractComponentFacade;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageOrder;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.basic.common.api.query.StringSearchOperator;

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

		return this.ucFindOrder.findOrder(id);
	}

	@Override
	public Page<OrderEto> findOrders(OrderSearchCriteriaTo criteria) {
		return this.ucFindOrder.findOrders(criteria);
	}

	@Override
	public OrderEto saveOrder(OrderEto order) {

		return this.ucManageOrder.saveOrder(order);
	}

	@Override
	public boolean deleteOrder(long id) {

		return this.ucManageOrder.deleteOrder(id);
	}

	@Override
	public CustomerEto findCustomer(long id) {

		return this.ucFindCustomer.findCustomer(id);
	}

	@Override
	public Page<CustomerEto> findCustomers(CustomerSearchCriteriaTo criteria) {
		return this.ucFindCustomer.findCustomers(criteria);
	}

	@Override
	public CustomerEto saveCustomer(CustomerEto customer) {

		return this.ucManageCustomer.saveCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(long id) {

		return this.ucManageCustomer.deleteCustomer(id);
	}

	@Override
	public ItemEto findItem(long id) {

		return this.ucFindItem.findItem(id);
	}

	@Override
	public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {
		return this.ucFindItem.findItems(criteria);
	}

	@Override
	public ItemEto saveItem(ItemEto item) {

		return this.ucManageItem.saveItem(item);
	}

	@Override
	public boolean deleteItem(long id) {
		return this.ucManageItem.deleteItem(id);
	}

	@Override
	public Page<ItemEto> findItemsWithNameLikeOrdered(String name){
		ItemSearchCriteriaTo criteria = createDefaultSearchCriteria(name);
		return ucFindItem.findItems(criteria);
	}

	@Override
	public Set<ItemEto> findByName(String name) {
		return ucFindItem.findByName(name);
	}

	@Override
	public void raiseItemPriceByOne(String name) {
		Set<ItemEto> itemsToReprice = ucFindItem.findByName(name);
		itemsToReprice.stream().forEach(item -> item.setPrice(item.getPrice() + 1.0));
		itemsToReprice.stream().forEach(item -> ucManageItem.saveItem(item));
	}

	private ItemSearchCriteriaTo createDefaultSearchCriteria(String name) {
		StringSearchOperator syntax = StringSearchOperator.LIKE;
		StringSearchConfigTo nameOption = StringSearchConfigTo.of(syntax);
		nameOption.setIgnoreCase(true);
		nameOption.setMatchSubstring(true);
		ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
		criteria.setName(name);
		criteria.setNameOption(nameOption);
		Sort sort = Sort.by("name");
		Pageable pageable = PageRequest.of(0, 20, sort);
		criteria.setPageable(pageable);
		return criteria;
	}

}
