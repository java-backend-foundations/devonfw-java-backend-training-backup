package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.Optional;
import java.util.Set;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractItemUc;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.basic.common.api.query.StringSearchOperator;

import io.micrometer.shaded.org.pcollections.MapPSet;

/**
 * Use case implementation for searching, filtering and getting Items
 */
@Named
@Validated
@Transactional
public class UcFindItemImpl extends AbstractItemUc implements UcFindItem {

	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(UcFindItemImpl.class);

	@Override
	public ItemEto findItem(long id) {
		LOG.debug("Get Item with id {} from database.", id);
		Optional<ItemEntity> foundEntity = getItemRepository().findById(id);
// TODO: mwypych, 2019-04-01: From my perspective it doesn't make sense to find by id and allow entity not to exists.
// If You agree - please replace findById to getOne which always return entity or throws exception
		if (foundEntity.isPresent())
			return getBeanMapper().map(foundEntity.get(), ItemEto.class);
		else
			return null;
	}

	@Override
	public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {
		Page<ItemEntity> items = getItemRepository().findByCriteria(criteria);
		return mapPaginatedEntityList(items, ItemEto.class);
	}

	@Override
	public Set<ItemEto> findByName(String name){
		Set<ItemEntity> items = getItemRepository().findByName(name);
		return getBeanMapper().mapSet(items, ItemEto.class);
	}

	@Override
	public Page<ItemEto> findItemsWithNameLikeOrdered(String name) {
		ItemSearchCriteriaTo criteria = createDefaultSearchCriteria(name);
		return this.findItems(criteria);
	}

	private ItemSearchCriteriaTo createDefaultSearchCriteria(String name) {
		// TODO: mwypych, 2019-04-01: Please extract to static method in new class called ItemSearchCriteriaUtil located next to search criteria
		StringSearchOperator syntax = StringSearchOperator.LIKE;
		StringSearchConfigTo nameOption = StringSearchConfigTo.of(syntax);
		nameOption.setIgnoreCase(true);
		nameOption.setMatchSubstring(true);
		ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
		criteria.setName(name);
		criteria.setNameOption(nameOption);

		// TODO: mwypych, 2019-04-01: extract name to constant (in created util)
		Sort sort = Sort.by("name");
		// TODO: mwypych, 2019-04-01: extract pagination properties to constants
		Pageable pageable = PageRequest.of(0, 20, sort);
		criteria.setPageable(pageable);
		return criteria;
	}

}
