package com.devonfw.app.java.order.general.common.base;

import javax.inject.Inject;

import com.devonfw.module.beanmapping.common.api.BeanMapper;

/**
 * This abstract class wraps advanced functionality according dozer mappings
 */
public abstract class AbstractBeanMapperSupport {

	/** @see #getBeanMapper() */
	@Inject
	private BeanMapper beanMapper;

	/**
	 * @return the {@link BeanMapper} instance.
	 */
	protected BeanMapper getBeanMapper() {

		return this.beanMapper;
	}

}
