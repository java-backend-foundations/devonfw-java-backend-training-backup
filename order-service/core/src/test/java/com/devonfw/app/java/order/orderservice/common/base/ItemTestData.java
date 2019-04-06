package com.devonfw.app.java.order.orderservice.common.base;

/**
 * The interface Item test data.
 */
public interface ItemTestData {

  /**
   * The constant FROG_NAME.
   */
  String FROG_NAME = "frog";
  /**
   * The constant DOG_NAME.
   */
  String DOG_NAME = "dog";
  /**
   * The constant CAT_NAME.
   */
  String CAT_NAME = "cat";

  /**
   * The constant FROG.
   */
  ItemEtoBuilder FROG = ItemEtoBuilder.anItemEto()
      .withName(FROG_NAME)
      .withPrice(12.50);

  /**
   * The constant DOG.
   */
  ItemEtoBuilder DOG = ItemEtoBuilder.anItemEto()
      .withName(DOG_NAME)
      .withPrice(5.50);

  /**
   * The constant CAT.
   */
  ItemEtoBuilder CAT = ItemEtoBuilder.anItemEto()
      .withName(CAT_NAME)
      .withPrice(12.50);

  /**
   * The constant CHEESE.
   */
  ItemEtoBuilder CHEESE = ItemEtoBuilder.anItemEto()
      .withName("cheese")
      .withPrice(12.50);

  /**
   * The constant CORN.
   */
  ItemEtoBuilder CORN = ItemEtoBuilder.anItemEto()
      .withName("corn")
      .withPrice(5.50);

}
