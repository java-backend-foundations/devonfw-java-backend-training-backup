package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageOrder;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractOrderUc;

/**
 * Use case implementation for modifying and deleting Orders
 */
@Named
@Validated
@Transactional
public class UcManageOrderImpl extends AbstractOrderUc implements UcManageOrder {

	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(UcManageOrderImpl.class);

	@Override
	public boolean deleteOrder(long orderId) {

		OrderEntity order = getOrderRepository().find(orderId);
		getOrderRepository().delete(order);
		LOG.debug("The order with id '{}' has been deleted.", orderId);
		return true;
	}

	@Override
	public OrderEto saveOrder(OrderEto order) {

		Objects.requireNonNull(order, "order");

		OrderEntity orderEntity = getBeanMapper().map(order, OrderEntity.class);

		// initialize, validate orderEntity here if necessary
		OrderEntity resultEntity = getOrderRepository().save(orderEntity);
		LOG.debug("Order with id '{}' has been created.", resultEntity.getId());
		return getBeanMapper().map(resultEntity, OrderEto.class);
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
		orderEto.setOwnerId(owner.getId());

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
