package com.devonfw.app.java.order.orderservice.logic.api.to;

import com.devonfw.app.java.order.orderservice.common.api.Customer;
import com.devonfw.module.basic.common.api.to.AbstractEto;

public class CustomerEto extends AbstractEto implements Customer {

	private static final long serialVersionUID = 1L;

	private String firstname;

	private String lastname;

	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String getLastname() {
		return lastname;
	}

	@Override
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



}
