package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import com.devonfw.app.java.order.orderservice.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;
import com.devonfw.app.java.order.orderservice.logic.base.usecase.AbstractItemUc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nonnull;
import javax.inject.Named;
import java.util.Objects;
import java.util.Set;

/**
 * Use case implementation for modifying and deleting Items
 */
@Named
@Validated
@Transactional
public class UcManageItemImpl extends AbstractItemUc implements UcManageItem {

  /**
   * Logger instance.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UcManageItemImpl.class);

  @Override
  public boolean deleteItem(long itemId) {
    ItemEntity item = getItemRepository().find(itemId);
    getItemRepository().delete(item);
    LOG.debug("The item with id '{}' has been deleted.", itemId);
    return true;
  }

  @Override
  public ItemEto saveItem(ItemEto item) {
    Objects.requireNonNull(item, "item");

    ItemEntity itemEntity = getBeanMapper().map(item, ItemEntity.class);

    // initialize, validate itemEntity here if necessary
    ItemEntity resultEntity = getItemRepository().save(itemEntity);
    LOG.debug("Item with id '{}' has been created.", resultEntity.getId());
    return getBeanMapper().map(resultEntity, ItemEto.class);
  }

  @Override
  public void raiseItemPrice(@Nonnull String name, @Nonnull Float price) {
    Set<ItemEto> itemsToReprice = getBeanMapper().mapSet(getItemRepository().findByName(name), ItemEto.class);
    if (!CollectionUtils.isEmpty(itemsToReprice)) {
      itemsToReprice.stream().forEach(item -> {
        item.setPrice(item.getPrice() + price);
        saveItem(item);
      });
    }
  }
}
