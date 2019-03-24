package com.devonfw.app.java.order.orderservice.logic.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.devonfw.app.java.order.general.logic.base.AbstractComponentFacade;
import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.dataaccess.api.repo.ItemRepository;
import com.devonfw.app.java.order.orderservice.logic.api.ItemManagement;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.basic.common.api.query.StringSearchOperator;

@Named
@Transactional
public class ItemManagementImpl extends AbstractComponentFacade implements ItemManagement{

	private ItemRepository itemRepository;

	public ItemManagementImpl() {
		super();
	}

	@Override
	public Set<ItemEto> findByName(String name){
		return getBeanMapper().mapSet(getItemRepository().findByName(name), ItemEto.class);
	}

	@Override
	public List<ItemEto> findAll() {
		return getBeanMapper().mapList(getItemRepository().findAll(), ItemEto.class);
	}

	@Override
	public void saveItem(ItemEto item) {
		getItemRepository().save(getBeanMapper().map(item, ItemEntity.class));
	}

	@Override
	public void saveAll(List<ItemEto> items) {
		getItemRepository().saveAll(getBeanMapper().mapList(items, ItemEntity.class));
	}

	public void deleteAll() {
		getItemRepository().deleteAll();
	}


	@Override
	public List<ItemEto> findItemsWithNameLikeOrdered(String name) {
		ItemSearchCriteriaTo criteria = createDefaultSearchCriteria(name);
		return getBeanMapper().mapList(getItemRepository().findByCriteria(criteria).getContent(), ItemEto.class);
	}

	@Override
	public void updateItem(String name) {
		Set<ItemEto> itemToUpdate = getBeanMapper().mapSet(getItemRepository().findByName(name), ItemEto.class);
		itemToUpdate.stream().forEach(item -> item.setPrice(item.getPrice() + 1));
		getItemRepository().saveAll(getBeanMapper().mapSet(itemToUpdate, ItemEntity.class));
	}

	public ItemRepository getItemRepository() {
		return itemRepository;
	}

	@Inject
	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	private ItemSearchCriteriaTo createDefaultSearchCriteria(String name) {
		StringSearchOperator syntax = StringSearchOperator.LIKE;
		StringSearchConfigTo nameOption = StringSearchConfigTo.of(syntax);
		nameOption.setIgnoreCase(true);
		nameOption.setMatchSubstring(true);
		ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
		criteria.setName(name);
		criteria.setNameOption(nameOption);
		Sort sort = Sort.by("name");
		Pageable pageable = PageRequest.of(0, 20, sort);
		criteria.setPageable(pageable);
		return criteria;
	}


}
