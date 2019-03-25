package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.Objects;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.orderservice.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageCustomer;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractCustomerUc;

/**
 * Use case implementation for modifying and deleting Customers
 */
@Named
@Validated
@Transactional
public class UcManageCustomerImpl extends AbstractCustomerUc implements UcManageCustomer {

	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(UcManageCustomerImpl.class);

	@Override
	public boolean deleteCustomer(long customerId) {

		CustomerEntity customer = getCustomerRepository().find(customerId);
		getCustomerRepository().delete(customer);
		LOG.debug("The customer with id '{}' has been deleted.", customerId);
		return true;
	}

	@Override
	public CustomerEto saveCustomer(CustomerEto customer) {

		Objects.requireNonNull(customer, "customer");

		CustomerEntity customerEntity = getBeanMapper().map(customer, CustomerEntity.class);

		// initialize, validate customerEntity here if necessary
		CustomerEntity resultEntity = getCustomerRepository().save(customerEntity);
		LOG.debug("Customer with id '{}' has been created.", resultEntity.getId());
		return getBeanMapper().map(resultEntity, CustomerEto.class);
	}
}
