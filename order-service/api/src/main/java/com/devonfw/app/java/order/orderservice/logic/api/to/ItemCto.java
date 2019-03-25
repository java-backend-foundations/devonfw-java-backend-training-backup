package com.devonfw.app.java.order.orderservice.logic.api.to;

import com.devonfw.module.basic.common.api.to.AbstractCto;

/**
 * Composite transport object of Item
 */
public class ItemCto extends AbstractCto {

	private static final long serialVersionUID = 1L;

	private ItemEto item;

	public ItemEto getItem() {
		return item;
	}

	public void setItem(ItemEto item) {
		this.item = item;
	}

}
