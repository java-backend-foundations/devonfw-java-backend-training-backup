package com.devonfw.app.java.order.orderservice.dataaccess.api;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.devonfw.app.java.order.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.app.java.order.orderservice.common.api.Customer;

/**
 * @author ARMIKA
 */
@Entity(name = "Customer")
public class CustomerEntity extends ApplicationPersistenceEntity implements Customer {

  private String firstname;

  private String lastname;

  private Set<OrderEntity> orders;

  private static final long serialVersionUID = 1L;

  /**
   * @return orders
   */
  @OneToMany(mappedBy = "owner")
  public Set<OrderEntity> getOrders() {

    return Collections.unmodifiableSet(this.orders);
  }

  /**
   * @param orders new value of {@link #getorders}.
   */
  public void setOrders(@NotNull Set<OrderEntity> orders) {

    this.orders.clear();
    this.orders.addAll(orders);
  }

  /**
   * @return firstname
   */
  @Override
  public String getFirstname() {

    return this.firstname;
  }

  /**
   * @param firstname new value of {@link #getfirstname}.
   */
  @SuppressWarnings("javadoc")
  @Override
  public void setFirstname(String firstname) {

    this.firstname = firstname;
  }

  /**
   * @return lastname
   */
  @Override
  public String getLastname() {

    return this.lastname;
  }

  /**
   * @param lastname new value of {@link #getlastname}.
   */
  @SuppressWarnings("javadoc")
  @Override
  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

}
