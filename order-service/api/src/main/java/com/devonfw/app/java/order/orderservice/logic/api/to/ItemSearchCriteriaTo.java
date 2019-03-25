package com.devonfw.app.java.order.orderservice.logic.api.to;

import com.devonfw.app.java.order.general.common.api.to.AbstractSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;

/**
 * {@link SearchCriteriaTo} to find instances of
 * {@link com.devonfw.app.java.order.orderservice.common.api.Item}s.
 */
public class ItemSearchCriteriaTo extends AbstractSearchCriteriaTo {

	private static final long serialVersionUID = 1L;

	private String name;

	private String description;

	private Double price;

	private StringSearchConfigTo nameOption;

	private StringSearchConfigTo descriptionOption;

	/**
	 * @return nameId
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name setter for name attribute
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return descriptionId
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description setter for description attribute
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return priceId
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price setter for price attribute
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the {@link StringSearchConfigTo} used to search for {@link #getName()
	 *         name}.
	 */
	public StringSearchConfigTo getNameOption() {

		return this.nameOption;
	}

	/**
	 * @param nameOption new value of {@link #getNameOption()}.
	 */
	public void setNameOption(StringSearchConfigTo nameOption) {

		this.nameOption = nameOption;
	}

	/**
	 * @return the {@link StringSearchConfigTo} used to search for
	 *         {@link #getDescription() description}.
	 */
	public StringSearchConfigTo getDescriptionOption() {

		return this.descriptionOption;
	}

	/**
	 * @param descriptionOption new value of {@link #getDescriptionOption()}.
	 */
	public void setDescriptionOption(StringSearchConfigTo descriptionOption) {

		this.descriptionOption = descriptionOption;
	}

}
