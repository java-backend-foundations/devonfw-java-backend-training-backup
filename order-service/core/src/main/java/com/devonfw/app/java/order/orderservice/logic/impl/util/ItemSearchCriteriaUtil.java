package com.devonfw.app.java.order.orderservice.logic.impl.util;

import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.basic.common.api.query.StringSearchOperator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * The type Item search criteria util.
 */
public class ItemSearchCriteriaUtil {
    /**
     * The constant SORT_BY_NAME.
     */
    public static final Sort SORT_BY_NAME = Sort.by("name");

    /**
     * The constant PAGINATION_PROPERTIES.
     */
    public static final Pageable PAGINATION_PROPERTIES = PageRequest.of(0, 20, SORT_BY_NAME);

    /**
     * Create default search criteria item search criteria to.
     *
     * @param name the name
     * @return the item search criteria to
     */
    public static ItemSearchCriteriaTo createDefaultSearchCriteria(String name) {
        StringSearchOperator syntax = StringSearchOperator.LIKE;
        StringSearchConfigTo nameOption = StringSearchConfigTo.of(syntax);
        nameOption.setIgnoreCase(true);
        nameOption.setMatchSubstring(true);
        ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
        criteria.setName(name);
        criteria.setNameOption(nameOption);
        criteria.setPageable(ItemSearchCriteriaUtil.PAGINATION_PROPERTIES);
        return criteria;
    }
}
