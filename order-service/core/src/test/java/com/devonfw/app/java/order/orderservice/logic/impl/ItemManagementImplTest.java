package com.devonfw.app.java.order.orderservice.logic.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devonfw.app.java.order.orderservice.logic.api.ItemManagement;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.module.test.common.base.ComponentTest;

@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT)
public class ItemManagementImplTest extends ComponentTest {

	@Inject
	private ItemManagement itemManagement;

	@Override
	protected void doTearDown() {
		super.doTearDown();
		this.itemManagement.deleteAll();
	}

	@Test
	public void shouldSearchForNameLikeOrdered() {

		//given
		prepareItems();
		String name = "Og";

		//when
		List<ItemEto> searchResult = itemManagement.findItemsWithNameLikeOrdered(name);

		//then
		assertThat(searchResult).hasSize(2);
		assertThat(searchResult).extracting("name").containsExactly("dog", "frog");
	}

	@Test
	public void shouldIncreasePriceByOne() {
		//given
		prepareItems();
		String name = "dog";
		//when
		itemManagement.updateItem(name);
		//then
		Set<ItemEto> updatedItems = itemManagement.findByName("dog");
		assertThat(updatedItems).extracting("price").containsOnly(6.50);

	}

	private void prepareItems() {
		ItemEto item1 = new ItemEto();
		String item1Name = "frog";
		item1.setName(item1Name);
		item1.setPrice(12.50);

		ItemEto item2 = new ItemEto();
		String item2Name = "dog";
		item2.setName(item2Name);
		item2.setPrice(5.50);

		ItemEto item3 = new ItemEto();
		String item3Name = "cat";
		item3.setName(item3Name);
		item3.setPrice(12.50);
		itemManagement.saveAll(Arrays.asList(item1, item2, item3));
	}

}
