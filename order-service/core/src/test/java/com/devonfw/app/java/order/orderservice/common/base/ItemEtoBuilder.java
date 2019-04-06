package com.devonfw.app.java.order.orderservice.common.base;

import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;

/**
 * The type Item eto builder.
 */
public final class ItemEtoBuilder {
  private String name;
  private String description;
  private Double price;
  private Long id;
  private int modificationCounter;

  private ItemEtoBuilder() {
  }

  /**
   * An item eto item eto builder.
   *
   * @return the item eto builder
   */
  public static ItemEtoBuilder anItemEto() {
    return new ItemEtoBuilder();
  }

  /**
   * With name item eto builder.
   *
   * @param name the name
   * @return the item eto builder
   */
  public ItemEtoBuilder withName(String name) {
    this.name = name;
    return this;
  }

  /**
   * With description item eto builder.
   *
   * @param description the description
   * @return the item eto builder
   */
  public ItemEtoBuilder withDescription(String description) {
    this.description = description;
    return this;
  }

  /**
   * With price item eto builder.
   *
   * @param price the price
   * @return the item eto builder
   */
  public ItemEtoBuilder withPrice(Double price) {
    this.price = price;
    return this;
  }

  /**
   * With id item eto builder.
   *
   * @param id the id
   * @return the item eto builder
   */
  public ItemEtoBuilder withId(Long id) {
    this.id = id;
    return this;
  }

  /**
   * With modification counter item eto builder.
   *
   * @param modificationCounter the modification counter
   * @return the item eto builder
   */
  public ItemEtoBuilder withModificationCounter(int modificationCounter) {
    this.modificationCounter = modificationCounter;
    return this;
  }

  /**
   * Build item eto.
   *
   * @return the item eto
   */
  public ItemEto build() {
    ItemEto itemEto = new ItemEto();
    itemEto.setName(name);
    itemEto.setDescription(description);
    itemEto.setPrice(price);
    itemEto.setId(id);
    itemEto.setModificationCounter(modificationCounter);
    return itemEto;
  }
}
