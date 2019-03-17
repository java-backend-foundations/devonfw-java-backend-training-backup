package com.devonfw.app.java.order.orderservice.logic.api.to;

import com.devonfw.app.java.order.general.common.api.to.AbstractSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;

/**
 * {@link SearchCriteriaTo} to find instances of
 * {@link com.devonfw.app.java.order.orderservice.common.api.Customer}s.
 */
public class CustomerSearchCriteriaTo extends AbstractSearchCriteriaTo {

	private static final long serialVersionUID = 1L;

	private String firstname;

	private String lastname;

	private StringSearchConfigTo firstnameOption;

	private StringSearchConfigTo lastnameOption;

	/**
	 * @return firstnameId
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname setter for firstname attribute
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return lastnameId
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname setter for lastname attribute
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the {@link StringSearchConfigTo} used to search for
	 *         {@link #getFirstname() firstname}.
	 */
	public StringSearchConfigTo getFirstnameOption() {

		return this.firstnameOption;
	}

	/**
	 * @param firstnameOption new value of {@link #getFirstnameOption()}.
	 */
	public void setFirstnameOption(StringSearchConfigTo firstnameOption) {

		this.firstnameOption = firstnameOption;
	}

	/**
	 * @return the {@link StringSearchConfigTo} used to search for
	 *         {@link #getLastname() lastname}.
	 */
	public StringSearchConfigTo getLastnameOption() {

		return this.lastnameOption;
	}

	/**
	 * @param lastnameOption new value of {@link #getLastnameOption()}.
	 */
	public void setLastnameOption(StringSearchConfigTo lastnameOption) {

		this.lastnameOption = lastnameOption;
	}

}
