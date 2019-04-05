package com.devonfw.app.java.order.general.service.impl.rest;

import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class OrderServiceRestTestHelper {

    public static final String DUMMY_NAME = "dummyName";
    public static final String DUMMY_NAME_TWO = "dummyNameTwo";
    public static final Double DUMMY_PRICE = 6.00;
    public static final Float PRICE_RAISE = 2.00F;
    public static final LocalDate DATE = LocalDate.of(2019, 03, 15);
    public static final OrderStatus ORDER_STATUS = OrderStatus.NEW;


    public OrderEto createDummyOrderEto(){
        OrderEto orderEto = new OrderEto();
        orderEto.setCreationDate(DATE);
        orderEto.setStatus(ORDER_STATUS);

        return orderEto;
    }

    public CustomerEto createDummyCustomer(){
        CustomerEto customerEto = new CustomerEto();
        customerEto.setFirstname(DUMMY_NAME);
        customerEto.setLastname(DUMMY_NAME);
        return customerEto;
    }

    public ItemEto createDummyItem(){
        ItemEto itemEto = new ItemEto();
        itemEto.setName(DUMMY_NAME);
        itemEto.setPrice(DUMMY_PRICE);
        return itemEto;
    }

    public ItemEto createDummyItemTwo(){
        ItemEto itemEto = new ItemEto();
        itemEto.setName(DUMMY_NAME_TWO);
        itemEto.setPrice(DUMMY_PRICE);
        return itemEto;
    }

    public OrderCto createDummyOrderCTO(){
        OrderCto orderCto = new OrderCto();
        orderCto.setOrder(createDummyOrderEto());
        orderCto.setOwner(createDummyCustomer());
        orderCto.setOrderPositions(new HashSet<>(Arrays.asList(createDummyItem(), createDummyItemTwo())));
        return orderCto;
    }
}
