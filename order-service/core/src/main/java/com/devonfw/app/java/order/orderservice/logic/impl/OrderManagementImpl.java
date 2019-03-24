package com.devonfw.app.java.order.orderservice.logic.impl;

import javax.inject.Named;
import javax.transaction.Transactional;

import com.devonfw.app.java.order.general.logic.base.AbstractComponentFacade;
import com.devonfw.app.java.order.orderservice.logic.api.OrderManagement;

@Named
@Transactional
public class OrderManagementImpl extends AbstractComponentFacade implements OrderManagement {

}
