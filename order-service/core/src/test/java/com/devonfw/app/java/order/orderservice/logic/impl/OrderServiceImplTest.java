package com.devonfw.app.java.order.orderservice.logic.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.module.test.common.base.ComponentTest;

@Transactional
@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrderServiceImplTest extends ComponentTest {

	@Inject
	private Orderservice orderService;

	@Test
	public void shouldSearchForNameLikeOrdered() {

		//given
		prepareItems();
		String name = "Og";

		//when
		Page<ItemEto> searchResult = orderService.findItemsWithNameLikeOrdered(name);

		//then
		assertThat(searchResult).hasSize(2);
		assertThat(searchResult).extracting("name").containsExactly("dog", "frog");
	}

	@Test
	public void shouldIncreasePriceByOne() {
		//given
		prepareItems();
		String name = "dog";
		//when
		orderService.raiseItemPriceByOne(name);
		//then
		Set<ItemEto> updatedItems = orderService.findByName("dog");
		assertThat(updatedItems).extracting("price").containsOnly(6.50);

	}

	@Test
	public void shouldFindItemByDateAndStatus(){
		//given
		CustomerEto owner = new CustomerEto();
		owner = orderService.saveCustomer(owner);

		OrderEto orderToFind = new OrderEto();
		OrderStatus status = OrderStatus.NEW;
		orderToFind.setStatus(status);
		LocalDate creationDate = LocalDate.of(2019, 03, 15);
		orderToFind.setCreationDate(creationDate);
		orderToFind.setOwnerId(owner.getId());
		orderToFind = orderService.saveOrder(orderToFind);

		OrderEto order2 = new OrderEto();
		order2.setStatus(OrderStatus.PAID);
		order2.setCreationDate(creationDate);
		order2.setOwnerId(owner.getId());
		order2 = orderService.saveOrder(order2);

		// when
		Set<OrderEto> foundOrders = orderService.findOrdersByCreationDateAndStatus(creationDate, status);

		// then
		assertThat(foundOrders).hasSize(1);
		assertThat(foundOrders).extracting("id").containsOnly(orderToFind.getId());
		assertThat(foundOrders).extracting("creationDate").containsOnly(creationDate);
		assertThat(foundOrders).extracting("status").containsOnly(status);
	}

	@Test
	public void shouldSaveOrderWthTwoPositions() {
		//given
				CustomerEto owner = new CustomerEto();
				String ownerFirstname = "Mickey";
				owner.setFirstname(ownerFirstname);
				owner.setLastname("Mouse");

				ItemEto item1 = new ItemEto();
				String item1Name = "cheese";
				item1.setName(item1Name);
				item1.setPrice(12.50);

				ItemEto item2 = new ItemEto();
				String item2Name = "corn";
				item2.setName(item2Name);
				item2.setPrice(5.50);

				Set<ItemEto> orderPositions = new HashSet<>(Arrays.asList(item1, item2));

				OrderEto order = new OrderEto();
				OrderStatus status = OrderStatus.NEW;
				order.setStatus(status);
				LocalDate creationDate = LocalDate.of(2019, 03, 15);
				order.setCreationDate(creationDate);
				order.setOwnerId(owner.getId());

				OrderCto orderCto = new OrderCto();
				orderCto.setOrderPositions(orderPositions);
				orderCto.setOrder(order);
				orderCto.setOwner(owner);

				// when
				OrderCto savedOrderCto = orderService.saveOrder(orderCto);

				// then
				assertThat(savedOrderCto.getOrder()).extracting("status").containsOnly(status);
				assertThat(savedOrderCto.getOrderPositions()).extracting("name").containsOnly(item2Name, item1Name);
				assertThat(savedOrderCto.getOwner()).extracting("firstname").containsOnly(ownerFirstname);
	}

	private void prepareItems() {
		ItemEto item1 = new ItemEto();
		String item1Name = "frog";
		item1.setName(item1Name);
		item1.setPrice(12.50);
		orderService.saveItem(item1);

		ItemEto item2 = new ItemEto();
		String item2Name = "dog";
		item2.setName(item2Name);
		item2.setPrice(5.50);
		orderService.saveItem(item2);

		ItemEto item3 = new ItemEto();
		String item3Name = "cat";
		item3.setName(item3Name);
		item3.setPrice(12.50);
		orderService.saveItem(item3);
	}

}
