package com.devonfw.app.java.order.orderservice.logic.api.to;

import java.time.LocalDate;

import com.devonfw.app.java.order.orderservice.common.api.Order;
import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.module.basic.common.api.to.AbstractEto;

public class OrderEto extends AbstractEto implements Order {

	private static final long serialVersionUID = 1L;

	private Double price;

	private OrderStatus status;

	private LocalDate creationDate;

	private Long ownerId;

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public OrderStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public LocalDate getCreationDate() {
		return creationDate;
	}

	@Override
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public Long getOwnerId() {
		return ownerId;
	}


	@Override
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}


}
