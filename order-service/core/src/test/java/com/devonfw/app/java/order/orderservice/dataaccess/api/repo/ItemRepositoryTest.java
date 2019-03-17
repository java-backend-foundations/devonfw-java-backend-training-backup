package com.devonfw.app.java.order.orderservice.dataaccess.api.repo;

import java.util.Arrays;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.basic.common.api.query.StringSearchOperator;
import com.devonfw.module.test.common.base.ComponentTest;

@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT)
public class ItemRepositoryTest extends ComponentTest {

	@Inject
	private ItemRepository itemRepository;

	@Override
	protected void doTearDown() {
		super.doTearDown();
		this.itemRepository.deleteAll();
	}

	@Test
	public void shouldFindItemsOrderedByNameWhereNameLikeOGCaseInsensitive() {
		// given
		prepareItems();
		ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
		criteria.setName("OG");
		StringSearchOperator syntax = StringSearchOperator.LIKE;
		StringSearchConfigTo nameOption = StringSearchConfigTo.of(syntax);
		nameOption.setIgnoreCase(true);
		nameOption.setMatchSubstring(true);
		criteria.setNameOption(nameOption);
		Sort sort = Sort.by("name");
		Pageable pageable = PageRequest.of(0, 20, sort);
		criteria.setPageable(pageable);

		// when
		Page<ItemEntity> foundItems = itemRepository.findByCriteria(criteria);

		// then
		assertThat(foundItems).hasSize(2);
		assertThat(foundItems).extracting("name").containsExactly("dog", "frog");
	}

	@Test
	public void shouldNotFindItemsOrderedByNameWhereNameLikeOGCaseInsensitive() {
		// given
		prepareItems();
		ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
		criteria.setName("OG");
		StringSearchOperator syntax = StringSearchOperator.LIKE;
		StringSearchConfigTo nameOption = StringSearchConfigTo.of(syntax);
		nameOption.setIgnoreCase(false);
		criteria.setNameOption(nameOption);
		Sort sort = Sort.by("name");
		Pageable pageable = PageRequest.of(0, 20, sort);
		criteria.setPageable(pageable );

		// when
		Page<ItemEntity> foundItems = itemRepository.findByCriteria(criteria);

		// then
		assertThat(foundItems).isEmpty();
	}

	@Test
	public void shouldNotFindItemsOrderedByNameWhereNameLikeCATS() {
		// given
		prepareItems();
		ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
		criteria.setName("CATS");
		StringSearchOperator syntax = StringSearchOperator.LIKE;
		StringSearchConfigTo nameOption = StringSearchConfigTo.of(syntax);
		nameOption.setIgnoreCase(true);
		criteria.setNameOption(nameOption);
		Sort sort = Sort.by("name");
		Pageable pageable = PageRequest.of(0, 20, sort);
		criteria.setPageable(pageable );

		// when
		Page<ItemEntity> foundItems = itemRepository.findByCriteria(criteria);

		// then
		assertThat(foundItems).isEmpty();
	}

	private void prepareItems() {
		ItemEntity item1 = new ItemEntity();
		String item1Name = "frog";
		item1.setName(item1Name);
		item1.setPrice(12.50);

		ItemEntity item2 = new ItemEntity();
		String item2Name = "dog";
		item2.setName(item2Name);
		item2.setPrice(5.50);

		ItemEntity item3 = new ItemEntity();
		String item3Name = "cat";
		item3.setName(item3Name);
		item3.setPrice(12.50);
		itemRepository.saveAll(Arrays.asList(item1, item2, item3));
	}

	@Test
	public void shouldUpdateItemPrice() {
		// given
		prepareItems();

		// when
		Set<ItemEntity> itemsToUpdate = itemRepository.findByName("dog");
		itemsToUpdate.stream().forEach(item -> item.setPrice(item.getPrice() + 1));
		itemRepository.saveAll(itemsToUpdate);

		// then
		Set<ItemEntity> updatedItems = itemRepository.findByName("dog");
		assertThat(updatedItems).extracting("price").containsOnly(6.50);
	}
}
