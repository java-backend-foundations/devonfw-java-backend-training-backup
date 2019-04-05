package com.devonfw.app.java.order.general.service.impl.rest;

import com.devonfw.app.java.order.general.service.base.test.RestServiceTest;
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
import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OrderServiceRestTestConfig.class)
public class OrderServiceRestTest extends RestServiceTest {

   // private OrderserviceRestService service;

    @Inject
    private ServiceClientFactory serviceClientFactory;

    @Inject
    private OrderServiceRestTestHelper helper;

    @Inject
    private OrderserviceRestService service;

    @Override
    public void doSetUp() {

        super.doSetUp();
        getDbTestHelper().resetDatabase();
       // this.service = this.serviceClientFactory.create(OrderserviceRestService.class);
    }

    @Override
    public void doTearDown(){
       // this.service = null;
        super.doTearDown();
    }

    @Test
    public void shouldFindItemByName(){
        //given
        OrderCto order = this.helper.createDummyOrderCTO();
        OrderCto createdOrder = this.service.saveOrder(order);
        //when
        Set<ItemEto> items = this.service.findItemByName(helper.DUMMY_NAME);
        //then
        assertThat(items).hasSize(1);
        assertThat(items).extracting("name").containsExactly(helper.DUMMY_NAME);
    }

    @Test
    public void shouldFindOrderByDateAndStatus(){
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
    public void shouldRaiseItemPriceByOne(){
        //given
        OrderCto order = this.helper.createDummyOrderCTO();
        OrderCto createdOrder = this.service.saveOrder(order);
        //when
        this.service.raiseItemPriceByOne(helper.DUMMY_NAME);
        Set<ItemEto> items = this.service.findItemByName(helper.DUMMY_NAME);
        //then
        assertThat(items).extracting("price").containsExactly(helper.DUMMY_PRICE+1);
    }

    @Test
    public void shouldDeleteCustomer(){
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
