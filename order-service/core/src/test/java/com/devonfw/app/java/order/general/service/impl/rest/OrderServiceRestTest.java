package com.devonfw.app.java.order.general.service.impl.rest;

import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.devonfw.app.java.order.SpringBootApp;
import com.devonfw.app.java.order.general.base.AbstractRestServiceTest;
import com.devonfw.app.java.order.general.common.base.test.DbTestHelper;
import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.common.base.CustomerTestData;
import com.devonfw.app.java.order.orderservice.common.base.ItemTestData;
import com.devonfw.app.java.order.orderservice.common.base.OrderTestData;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.CustomerRepository;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.service.api.rest.OrderserviceRestService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SpringBootApp.class, OrderServiceRestTestConfig.class })
public class OrderServiceRestTest extends AbstractRestServiceTest {

	@Inject
	private OrderServiceRestTestHelper helper;

	@Inject
	private OrderserviceRestService service;

	@Inject
	private DbTestHelper dbTestHelper;

	@Inject
	private Orderservice orderservice;

	@Inject
	private CustomerRepository customerRepository;

	@Override
	public void doSetUp() {

		super.doSetUp();
		dbTestHelper.resetDatabase();
	}

	@Override
	public void doTearDown() {
		this.service = null;
		super.doTearDown();
	}

	@Test
	public void shouldFindItemByName() {
		// given
		OrderCto order = this.helper.createDummyOrderCTO();
		this.service.saveOrder(order);
		// when
		Set<ItemEto> items = this.service.findItemByName(ItemTestData.CORN_NAME);
		// then
		assertThat(items).hasSize(1);
		assertThat(items.iterator().next().getName()).isEqualTo(ItemTestData.CORN_NAME);
	}

	@Test
	public void shouldFindOrderByDateAndStatus() {
		// given
		OrderCto order = this.helper.createDummyOrderCTO();
		OrderCto createdOrder = this.service.saveOrder(order);
		// when
		Set<OrderEto> foundOrders = this.service.findOrdersByCreationDateAndStatus(OrderTestData.CREATION_DATE,
				OrderStatus.NEW);
		// then
		assertThat(foundOrders).hasSize(1);
		OrderEto foundOrder = foundOrders.iterator().next();
		assertThat(foundOrder.getId()).isEqualTo(createdOrder.getOrder().getId());
		assertThat(foundOrder.getCreationDate()).isEqualTo(OrderTestData.CREATION_DATE);
		assertThat(foundOrder.getStatus()).isEqualTo(OrderStatus.NEW);
	}

	@Test
	public void shouldRaiseItemPriceByOne() {
		// given
		OrderCto order = this.helper.createDummyOrderCTO();
		this.service.saveOrder(order);
		// when
		this.service.raiseItemPrice(ItemTestData.CORN_NAME, Float.valueOf(1));
		Set<ItemEto> items = this.service.findItemByName(ItemTestData.CORN_NAME);
		assertThat(items.size()).isEqualTo(1);
		// then
		ItemEto foundItem = items.iterator().next();
		assertThat(foundItem.getPrice()).isEqualTo(ItemTestData.CORN.build().getPrice() + Float.valueOf(1));
	}

	@Test
	public void shouldDeleteCustomer() {
		// given
		CustomerEto customerToBeDeleted = CustomerTestData.MICKEY_MOUSE.build();
		CustomerEto savedCustomer = orderservice.saveCustomer(customerToBeDeleted);

		assertThat(customerRepository.count()).isEqualTo(1);
		// when
		this.service.deleteCustomer(savedCustomer.getId());
		// then
		assertThat(customerRepository.count()).isEqualTo(0);
	}

}
