package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.OrderEntity;
import com.devonfw.module.test.common.base.ComponentTest;

@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrderRepositoryTest extends ComponentTest {

	@Inject
	private OrderRepository orderRepository;

	@Inject
	private CustomerRepository customerRepository;

	@Inject
	private ItemRepository itemRepository;

	@Override
	protected void doTearDown() {
		super.doTearDown();
		this.orderRepository.deleteAll();
		this.customerRepository.deleteAll();
		this.itemRepository.deleteAll();
	}

	@Test
	public void shouldFindOneWithGivenDateAndStatusWhenOtherDateIsDifferent() {
		//given
		CustomerEntity owner = new CustomerEntity();
		customerRepository.save(owner);

		OrderEntity orderToFind = new OrderEntity();
		OrderStatus status = OrderStatus.NEW;
		orderToFind.setStatus(status);
		LocalDate creationDate = LocalDate.of(2019, 03, 15);
		orderToFind.setCreationDate(creationDate);
		orderToFind.setOwner(owner);
		orderRepository.save(orderToFind);

		OrderEntity order2 = new OrderEntity();
		order2.setStatus(status);
		order2.setCreationDate(LocalDate.of(2019, 04, 15));
		order2.setOwner(owner);
		orderRepository.save(order2);

		// when
		Set<OrderEntity> foundOrders = orderRepository.findAllByCreationDateAndStatus(creationDate, status);

		// then
		assertThat(foundOrders).hasSize(1);
		assertThat(foundOrders).extracting("id").containsOnly(orderToFind.getId());
		assertThat(foundOrders).extracting("creationDate").containsOnly(creationDate);
		assertThat(foundOrders).extracting("status").containsOnly(status);
	}

	@Test
	public void shouldFindOneWithGivenDateAndStatusWhenOtherStatusIsDifferent() {
		//given
		CustomerEntity owner = new CustomerEntity();
		customerRepository.save(owner);

		OrderEntity orderToFind = new OrderEntity();
		OrderStatus status = OrderStatus.NEW;
		orderToFind.setStatus(status);
		LocalDate creationDate = LocalDate.of(2019, 03, 15);
		orderToFind.setCreationDate(creationDate);
		orderToFind.setOwner(owner);
		orderRepository.save(orderToFind);

		OrderEntity order2 = new OrderEntity();
		order2.setStatus(OrderStatus.PAID);
		order2.setCreationDate(creationDate);
		order2.setOwner(owner);
		orderRepository.save(order2);

		// when
		Set<OrderEntity> foundOrders = orderRepository.findAllByCreationDateAndStatus(creationDate, status);

		// then
		assertThat(foundOrders).hasSize(1);
		assertThat(foundOrders).extracting("id").containsOnly(orderToFind.getId());
		assertThat(foundOrders).extracting("creationDate").containsOnly(creationDate);
		assertThat(foundOrders).extracting("status").containsOnly(status);
	}

	@Test
	public void shouldCreateOrderWithOwnerAndTwoPositions() {
		//given
		CustomerEntity owner = new CustomerEntity();
		String ownerFirstname = "Mickey";
		owner.setFirstname(ownerFirstname);
		owner.setLastname("Mouse");

		ItemEntity item1 = new ItemEntity();
		String item1Name = "cheese";
		item1.setName(item1Name);
		item1.setPrice(12.50);

		ItemEntity item2 = new ItemEntity();
		String item2Name = "corn";
		item2.setName(item2Name);
		item2.setPrice(5.50);

		Set<ItemEntity> orderPositions = new HashSet<>(Arrays.asList(item1, item2));

		OrderEntity order = new OrderEntity();
		OrderStatus status = OrderStatus.NEW;
		order.setStatus(status);
		LocalDate creationDate = LocalDate.of(2019, 03, 15);
		order.setCreationDate(creationDate);
		order.setOwner(owner);
		order.setOrderPositions(orderPositions);

		// when
		customerRepository.save(owner);
		itemRepository.saveAll(orderPositions);
		OrderEntity savedOrder = orderRepository.save(order);

		// then
		assertThat(savedOrder).extracting("id").containsOnly(order.getId());
		assertThat(savedOrder.getOrderPositions()).extracting("name").containsOnly(item2Name, item1Name);
		assertThat(savedOrder.getOwner()).extracting("firstname").containsOnly(ownerFirstname);
	}
}
