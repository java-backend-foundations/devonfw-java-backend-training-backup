package com.devonfw.app.java.order.orderservice.common.api;

/**
 * Status of OrderEntity which is updated during processing order.
 *
 */
public enum OrderStatus {
	NEW, PREPARING, PREPARED, SERVED, PAID, CANCELLED
}
