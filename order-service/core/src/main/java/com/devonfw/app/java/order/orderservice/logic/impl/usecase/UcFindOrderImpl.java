package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractOrderUc;

/**
 * Use case implementation for searching, filtering and getting Orders
 */
@Named
@Validated
@Transactional
public class UcFindOrderImpl extends AbstractOrderUc implements UcFindOrder {

	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(UcFindOrderImpl.class);

	@Override
	public OrderEto findOrder(long id) {
		LOG.debug("Get Order with id {} from database.", id);
		Optional<OrderEntity> foundEntity = getOrderRepository().findById(id);
		if (foundEntity.isPresent())
			return getBeanMapper().map(foundEntity.get(), OrderEto.class);
		else
			return null;
	}

	@Override
	public Page<OrderEto> findOrders(OrderSearchCriteriaTo criteria) {
		Page<OrderEntity> orders = getOrderRepository().findByCriteria(criteria);
		return mapPaginatedEntityList(orders, OrderEto.class);
	}

	@Override
	public Set<OrderEto> findOrdersByCreationDateAndStatus(LocalDate creationDate, OrderStatus status){
		return getBeanMapper().mapSet(getOrderRepository()
				.findAllByCreationDateAndStatus(creationDate, status), OrderEto.class);
	}

}
