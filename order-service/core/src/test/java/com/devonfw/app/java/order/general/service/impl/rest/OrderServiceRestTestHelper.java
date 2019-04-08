package com.devonfw.app.java.order.general.service.impl.rest;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

/**
 * The type Order service rest test helper.
 */
public class OrderServiceRestTestHelper {

    /**
     * The constant DUMMY_NAME.
     */
    public static final String DUMMY_NAME = "dummyName";
    /**
     * The constant DUMMY_NAME_TWO.
     */
    public static final String DUMMY_NAME_TWO = "dummyNameTwo";
    /**
     * The constant DUMMY_PRICE.
     */
    public static final Double DUMMY_PRICE = 6.00;
    /**
     * The constant PRICE_RAISE.
     */
    public static final Float PRICE_RAISE = 2.00F;
    /**
     * The constant DATE.
     */
    public static final LocalDate DATE = LocalDate.of(2019, 03, 15);
    /**
     * The constant ORDER_STATUS.
     */
    public static final OrderStatus ORDER_STATUS = OrderStatus.NEW;


    /**
     * Create dummy order eto order eto.
     *
     * @return the order eto
     */
    public OrderEto createDummyOrderEto(){
        OrderEto orderEto = new OrderEto();
        orderEto.setCreationDate(DATE);
        orderEto.setStatus(ORDER_STATUS);

        return orderEto;
    }

    /**
     * Create dummy customer customer eto.
     *
     * @return the customer eto
     */
    public CustomerEto createDummyCustomer(){
        CustomerEto customerEto = new CustomerEto();
        customerEto.setFirstname(DUMMY_NAME);
        customerEto.setLastname(DUMMY_NAME);
        return customerEto;
    }

    /**
     * Create dummy item item eto.
     *
     * @return the item eto
     */
    public ItemEto createDummyItem(){
        ItemEto itemEto = new ItemEto();
        itemEto.setName(DUMMY_NAME);
        itemEto.setPrice(DUMMY_PRICE);
        return itemEto;
    }

    /**
     * Create dummy item two item eto.
     *
     * @return the item eto
     */
    public ItemEto createDummyItemTwo(){
        ItemEto itemEto = new ItemEto();
        itemEto.setName(DUMMY_NAME_TWO);
        itemEto.setPrice(DUMMY_PRICE);
        return itemEto;
    }

    /**
     * Create dummy order cto order cto.
     *
     * @return the order cto
     */
    public OrderCto createDummyOrderCTO(){
        OrderCto orderCto = new OrderCto();
        orderCto.setOrder(createDummyOrderEto());
        orderCto.setOwner(createDummyCustomer());
        orderCto.setOrderPositions(new HashSet<>(Arrays.asList(createDummyItem(), createDummyItemTwo())));
        return orderCto;
    }
}
