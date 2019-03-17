package com.devonfw.app.java.order.orderservice.dataaccess.api;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.devonfw.app.java.order.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.app.java.order.orderservice.common.api.Customer;

@Entity(name = "Customer")
public class CustomerEntity extends ApplicationPersistenceEntity implements Customer {

	private String firstname;

	private String lastname;

	private Set<OrderEntity> orders;

	private static final long serialVersionUID = 1L;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@OneToMany(mappedBy = "owner")
	public Set<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderEntity> orders) {
		this.orders = orders;
	}

}
