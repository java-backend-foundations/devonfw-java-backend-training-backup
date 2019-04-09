package com.devonfw.app.java.order.general.common.api.security;

import javax.inject.Named;

import com.devonfw.module.security.common.api.accesscontrol.AccessControlGroup;
import com.devonfw.module.security.common.base.accesscontrol.AccessControlConfig;

/**
 * Example of {@link AccessControlConfig} that used for testing.
 */
@Named
public class ApplicationAccessControlConfig extends AccessControlConfig {

  /**
   * The constant APP_ID.
   */
  public static final String APP_ID = "order-service";

  private static final String PREFIX = APP_ID + ".";

  /**
   * The constant PERMISSION_FIND_BINARY_OBJECT.
   */
  public static final String PERMISSION_FIND_BINARY_OBJECT = PREFIX + "FindBinaryObject";

  public static final String PERMISSION_FIND_ITEM = PREFIX + "FindItem";

  public static final String PERMISSION_FIND_CUSTOMER = PREFIX + "FindCustomer";

  public static final String PERMISSION_SAVE_ORDER = PREFIX + "SaveOrder";

  public static final String PERMISSION_FIND_ORDER = PREFIX + "FindOrder";

  public static final String PERMISSION_DELETE_ORDER = PREFIX + "DeleteOrder";


  /**
   * The constant PERMISSION_SAVE_BINARY_OBJECT.
   */
  public static final String PERMISSION_SAVE_BINARY_OBJECT = PREFIX + "SaveBinaryObject";

  /**
   * The constant PERMISSION_DELETE_BINARY_OBJECT.
   */
  public static final String PERMISSION_DELETE_BINARY_OBJECT = PREFIX + "DeleteBinaryObject";


  /**
   * The constant GROUP_READ_MASTER_DATA.
   */
  public static final String GROUP_READ_MASTER_DATA = PREFIX + "ReadMasterData";

  /**
   * The constant GROUP_ADMIN.
   */
  public static final String GROUP_ADMIN = PREFIX + "Admin";

  public static final String GROUP_COOK = PREFIX + "Cook";

  public static final String GROUP_BARKEEPER = PREFIX + "Barkeeper";

  public static final String GROUP_WAITER = PREFIX + "Waiter";

  public static final String GROUP_CHIEF = PREFIX + "Chief";

  public static final String PERMISSION_RAISE_ITEM_PRICE = PREFIX + "RaiseItemPrice";
  public static final String PERMISSION_DELETE_ITEM = PREFIX + "DeleteItem";
  public static final String PERMISSION_SAVE_ITEM = PREFIX + "SaveItem";
  public static final String PERMISSION_DELETE_CUSTOMER = PREFIX + "DeleteCustomer";
  public static final String PERMISSION_SAVE_CUSTOMER = PREFIX + "SaveCustomer";

  /**
   * The constructor.
   */
  public ApplicationAccessControlConfig() {

    super();
    AccessControlGroup readMasterData = group(GROUP_READ_MASTER_DATA, PERMISSION_FIND_BINARY_OBJECT, PERMISSION_FIND_ITEM, PERMISSION_FIND_CUSTOMER);
    AccessControlGroup cook = group(GROUP_COOK, readMasterData, PERMISSION_SAVE_BINARY_OBJECT, PERMISSION_DELETE_BINARY_OBJECT, PERMISSION_SAVE_ORDER, PERMISSION_FIND_ORDER);
    AccessControlGroup barkeeper = group(GROUP_BARKEEPER, cook, PERMISSION_DELETE_ORDER);
    AccessControlGroup waiter = group(GROUP_WAITER, barkeeper);
    AccessControlGroup chief = group(GROUP_CHIEF, waiter, PERMISSION_RAISE_ITEM_PRICE, PERMISSION_DELETE_ITEM, PERMISSION_SAVE_ITEM, PERMISSION_DELETE_CUSTOMER, PERMISSION_SAVE_CUSTOMER);

    group(GROUP_ADMIN, chief, PERMISSION_SAVE_BINARY_OBJECT, PERMISSION_DELETE_BINARY_OBJECT);
  }

}