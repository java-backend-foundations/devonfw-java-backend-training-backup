package com.devonfw.app.java.order.orderservice.common.api;

import com.devonfw.app.java.order.general.common.api.ApplicationEntity;

public interface Item extends ApplicationEntity {

  /**
   * @return nameId
   */
  public String getName();

  /**
   * @param name setter for name attribute
   */
  public void setName(String name);

  /**
   * @return descriptionId
   */
  public String getDescription();

  /**
   * @param description setter for description attribute
   */
  public void setDescription(String description);

  /**
   * @return priceId
   */
  public Double getPrice();

  /**
   * @param price setter for price attribute
   */
  public void setPrice(Double price);

}
