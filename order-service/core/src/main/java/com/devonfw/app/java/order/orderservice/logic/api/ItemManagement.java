package com.devonfw.app.java.order.orderservice.logic.api;

import java.util.List;
import java.util.Set;

import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;

public interface ItemManagement {

	List<ItemEto> findItemsWithNameLikeOrdered(String name);

	void updateItem(String name);

	List<ItemEto> findAll();

	void saveItem(ItemEto item);

	void saveAll(List<ItemEto> items);

	Set<ItemEto> findByName(String name);

	void deleteAll();

}
