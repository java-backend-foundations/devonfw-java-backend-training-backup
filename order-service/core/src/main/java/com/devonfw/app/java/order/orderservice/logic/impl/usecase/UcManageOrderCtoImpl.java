package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageOrderCto;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractOrderCtoUc;

@Named
@Validated
@Transactional
public class UcManageOrderCtoImpl extends AbstractOrderCtoUc implements UcManageOrderCto{

	@Override
	public OrderCto findOrderCto(Long id) {
		OrderCto result = new OrderCto();
		return result;
	}

	@Override
	public OrderCto saveOrder(OrderCto order) {
		Objects.requireNonNull(order, "order");
		Objects.requireNonNull(order.getOrder(), "orderEto");
		Objects.requireNonNull(order.getOwner(), "owner");
		Objects.requireNonNull(order.getOrderPositions(), "orderPositions");

		OrderEto orderEto = order.getOrder();
		CustomerEntity owner = getCustomerRepository().save(
				getBeanMapper().map(order.getOwner(), CustomerEntity.class));

		List<ItemEntity> itemEntities = getItemRepository().saveAll(
				getBeanMapper().mapSet(order.getOrderPositions(), ItemEntity.class));


		OrderEntity orderEntity = getBeanMapper().map(orderEto, OrderEntity.class);
		orderEntity.setOrderPositions(new HashSet<>(itemEntities));

		orderEto = getBeanMapper().map(getOrderRepository().save(orderEntity), OrderEto.class);
		order.setOrder(orderEto);
		order.setOrderPositions(new HashSet<>(getBeanMapper().mapList(itemEntities, ItemEto.class)));
		order.setOwner(getBeanMapper().map(owner, CustomerEto.class));

		return order;
	}

}
