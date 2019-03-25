package com.devonfw.app.java.order.orderservice.logic.api;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageCustomer;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageOrder;

/**
 * Interface for Orderservice component.
 */
public interface Orderservice
		extends UcFindOrder, UcManageOrder, UcFindCustomer, UcManageCustomer, UcFindItem, UcManageItem {

	void raiseItemPriceByOne(String name);

	Page<ItemEto> findItemsWithNameLikeOrdered(String name);

}
