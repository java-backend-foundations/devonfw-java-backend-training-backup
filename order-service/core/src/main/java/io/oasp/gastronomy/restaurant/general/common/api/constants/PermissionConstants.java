
package io.oasp.gastronomy.restaurant.general.common.api.constants;

import com.devonfw.module.security.common.api.accesscontrol.AccessControlPermission;

/**
 * Contains constants for the keys of all {@link AccessControlPermission
 * Permission}s.
 */
public abstract class PermissionConstants {

	/** {@link AccessControlPermission Permission} to find item. */
	public static final String FIND_ITEM = "FindItem";

	/** {@link AccessControlPermission Permission} to find customer. */
	public static final String FIND_CUSTOMER = "FindCustomer";

	/** {@link AccessControlPermission Permission} to delete order. */
	public static final String DELETE_ORDER = "DeleteOrder";

	/** {@link AccessControlPermission Permission} to save order. */
	public static final String SAVE_ORDER = "SaveOrder";

	/** {@link AccessControlPermission Permission} to find order. */
	public static final String FIND_ORDER = "FindOrder";

	/** {@link AccessControlPermission Permission} to raise item price. */
	public static final String RAISE_ITEM_PRICE = "RaiseItemPrice";

	/** {@link AccessControlPermission Permission} to delete item. */
	public static final String DELETE_ITEM = "DeleteItem";

	/** {@link AccessControlPermission Permission} to save item. */
	public static final String SAVE_ITEM = "SaveItem";

	/** {@link AccessControlPermission Permission} to delete customer. */
	public static final String DELETE_CUSTOMER = "DeleteCustomer";

	/** {@link AccessControlPermission Permission} to save customer. */
	public static final String SAVE_CUSTOMER = "SaveCustomer";

}