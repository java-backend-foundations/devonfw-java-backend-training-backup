package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.module.test.common.base.ComponentTest;

@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT)
public class CustomerRepositoryTest extends ComponentTest {

	@Inject
	private OrderRepository orderRepository;

	@Inject
	private CustomerRepository customerRepository;

	@Override
	protected void doTearDown() {
		super.doTearDown();
		this.orderRepository.deleteAll();
		this.customerRepository.deleteAll();
	}

	@Test
	public void shouldRemoveCustomerNotUsedAsOwner() {
		//given
		CustomerEntity customer = new CustomerEntity();
		String ownerFirstname = "Mickey";
		customer.setFirstname(ownerFirstname);
		customer.setLastname("Mouse");
		CustomerEntity savedCustomer = customerRepository.save(customer);
		assertThat(customerRepository.find(savedCustomer.getId())).isNotNull();

		// when
		customerRepository.deleteById(savedCustomer.getId());

		// then
		assertThat(customerRepository.findAll()).isEmpty();
	}
}
