package com.devonfw.app.java.order.orderservice.logic.impl;

import com.devonfw.app.java.order.orderservice.common.base.CustomerTestData;
import com.devonfw.app.java.order.orderservice.common.base.ItemTestData;
import com.devonfw.app.java.order.orderservice.common.base.OrderTestData;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.module.test.common.base.ComponentTest;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Order service impl test.
 */
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OrderServiceImplTest extends ComponentTest {

  @Inject
  private Orderservice orderService;

  /**
   * Should search for name like ordered.
   */
  @Test
  public void shouldSearchForNameLikeOrdered() {

    //given
    prepareItems();
    String name = "Og";

    //when
    Page<ItemEto> searchResult = orderService.findItemsWithNameLikeOrdered(name);

    //then
    assertThat(searchResult).isNotEmpty();
    assertThat(searchResult).hasSize(2);
    assertThat(searchResult.stream().map(ItemEto::getName).collect(Collectors.toList()))
        .containsExactly(ItemTestData.DOG_NAME, ItemTestData.FROG_NAME);
  }

  /**
   * Should increase price by one.
   */
  @Test
  public void shouldIncreasePriceByOne() {
    //given
    prepareItems();
    String name = ItemTestData.DOG_NAME;
    Float price = 1.0F;
    //when
    orderService.raiseItemPrice(name, price);
    //then
    Set<ItemEto> updatedItems = orderService.findByName(ItemTestData.DOG_NAME);
    assertThat(updatedItems).isNotEmpty();
    assertThat(updatedItems.stream().map(ItemEto::getPrice)).containsOnly(6.50);

  }

  /**
   * Should find item by date and status.
   */
  @Test
  public void shouldFindItemByDateAndStatus() {
    //given
    CustomerEto owner = CustomerTestData.SOME_CUSTOMER.build();
    owner = orderService.saveCustomer(owner);

    OrderEto orderToFind = OrderTestData.ORDER_NEW.build();
    orderToFind.setOwnerId(owner.getId());
    orderToFind = orderService.saveOrder(orderToFind);

    OrderEto order2 = OrderTestData.ORDER_PAID.build();
    order2.setOwnerId(owner.getId());
    orderService.saveOrder(order2);

    // when
    Set<OrderEto> foundOrders = orderService.findOrdersByCreationDateAndStatus(OrderTestData.CREATION_DATE, orderToFind.getStatus());

    // then
    assertThat(foundOrders).hasSize(1);
    assertThat(foundOrders.iterator().next()).isEqualToComparingFieldByField(orderToFind);
  }

  /**
   * Should save order wth two positions.
   */
  @Test
  public void shouldSaveOrderWthTwoPositions() {
    //given
    OrderCto orderCto = prepareOrderCto();

    // when
    OrderCto savedOrderCto = orderService.saveOrder(orderCto);

    // then
    assertThat(savedOrderCto).isNotNull();
    assertThat(savedOrderCto.getOrder()).isEqualToComparingFieldByField(orderCto.getOrder());
    assertThat(savedOrderCto.getOrderPositions()).isNotEmpty();
    assertThat(savedOrderCto.getOrderPositions().size()).isEqualTo(2);
    assertThat(savedOrderCto.getOwner()).isEqualToComparingFieldByField(orderCto.getOwner());
  }

  private OrderCto prepareOrderCto() {
    CustomerEto owner = CustomerTestData.MICKEY_MOUSE.build();
    Set<ItemEto> orderPositions = new HashSet<>(Arrays.asList(ItemTestData.CHEESE.build(), ItemTestData.CORN.build()));
    OrderEto order = OrderTestData.ORDER_NEW.build();
    order.setOwnerId(owner.getId());
    OrderCto orderCto = new OrderCto();
    orderCto.setOrderPositions(orderPositions);
    orderCto.setOrder(order);
    orderCto.setOwner(owner);
    return orderCto;
  }

  private void prepareItems() {
    orderService.saveItem(ItemTestData.FROG.build());
    orderService.saveItem(ItemTestData.DOG.build());
    orderService.saveItem(ItemTestData.CAT.build());
  }

}
