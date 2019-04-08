package com.devonfw.app.java.order.general.service.impl.rest;

import com.devonfw.app.java.order.general.base.AbstractRestServiceTest;
import com.devonfw.app.java.order.general.common.base.test.DbTestHelper;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.service.api.rest.OrderserviceRestService;
import com.devonfw.module.service.common.api.client.ServiceClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OrderServiceRestTestConfig.class})
public class OrderServiceRestTest extends AbstractRestServiceTest {

  @Inject
  private ServiceClientFactory serviceClientFactory;

  @Inject
  private OrderServiceRestTestHelper helper;

  private OrderserviceRestService service;

  @Inject
  private DbTestHelper dbTestHelper;

  @Override
  public void doSetUp() {

    super.doSetUp();
    dbTestHelper.resetDatabase();
    this.service = this.serviceClientFactory.create(OrderserviceRestService.class);
  }

  @Override
  public void doTearDown() {
    this.service = null;
    super.doTearDown();
  }

  @Test
  public void shouldFindItemByName() {
    //given
    OrderCto order = this.helper.createDummyOrderCTO();
    OrderCto createdOrder = this.service.saveOrder(order);
    //when
    Set<ItemEto> items = this.service.findItemByName(helper.DUMMY_NAME);
    //then
    assertThat(items).hasSize(1);
    assertThat(items.iterator().next().getName()).isEqualTo(helper.DUMMY_NAME);
  }

  @Test
  public void shouldFindOrderByDateAndStatus() {
    //given
    OrderCto order = this.helper.createDummyOrderCTO();
    OrderCto createdOrder = this.service.saveOrder(order);
    //when
    Set<OrderEto> foundOrders = this.service.findOrdersByCreationDateAndStatus(helper.DATE, helper.ORDER_STATUS);
    //then
    assertThat(foundOrders).hasSize(1);
    assertThat(foundOrders).extracting("id").containsOnly(createdOrder.getOrder().getId());
    assertThat(foundOrders).extracting("creationDate").containsOnly(helper.DATE);
    assertThat(foundOrders).extracting("status").containsOnly(helper.ORDER_STATUS);
  }

  @Test
  public void shouldRaiseItemPriceByOne() {
    //given
    OrderCto order = this.helper.createDummyOrderCTO();
    OrderCto createdOrder = this.service.saveOrder(order);
    //when
    this.service.raiseItemPrice(helper.DUMMY_NAME, helper.PRICE_RAISE);
    Set<ItemEto> items = this.service.findItemByName(helper.DUMMY_NAME);
    //then
    assertThat(items).extracting("price").containsExactly(helper.DUMMY_PRICE + helper.PRICE_RAISE);
  }

  @Test
  public void shouldDeleteCustomer() {
    //given
    OrderCto order = this.helper.createDummyOrderCTO();
    OrderCto createdOrder = this.service.saveOrder(order);
    //when
    this.service.deleteCustomer(createdOrder.getOwner().getId());
    Set<OrderEto> foundOrders = this.service.findOrdersByCreationDateAndStatus(helper.DATE, helper.ORDER_STATUS);
    //then
    assertThat(foundOrders).extracting("owner").containsExactly(null);
  }

}
