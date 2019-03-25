package com.devonfw.app.java.order.orderservice.logic.base.usecase;

import javax.inject.Inject;

import com.devonfw.app.java.order.general.logic.base.AbstractUc;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.CustomerRepository;

/**
 * Abstract use case for Customers, which provides access to the commonly
 * necessary data access objects.
 */
public class AbstractCustomerUc extends AbstractUc {

	/** @see #getCustomerRepository() */
	@Inject
	private CustomerRepository customerRepository;

	/**
	 * Returns the field 'customerRepository'.
	 * 
	 * @return the {@link CustomerRepository} instance.
	 */
	public CustomerRepository getCustomerRepository() {

		return this.customerRepository;
	}

}
