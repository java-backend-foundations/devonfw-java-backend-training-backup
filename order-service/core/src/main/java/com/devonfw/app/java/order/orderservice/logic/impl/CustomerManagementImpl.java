package com.devonfw.app.java.order.orderservice.logic.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devonfw.app.java.order.general.logic.base.AbstractComponentFacade;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.CustomerRepository;
import com.devonfw.app.java.order.orderservice.logic.api.CustomerManagement;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;

@Named
@Transactional
public class CustomerManagementImpl extends AbstractComponentFacade implements CustomerManagement {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerManagementImpl.class);

	private CustomerRepository customerRepository;

	public CustomerManagementImpl() {
		super();
	}

	@Override
	public void deleteCustomerById(Long customerId) {
		getCustomerRepository().deleteById(customerId);
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	@Inject
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}




}
