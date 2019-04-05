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

  /**
   * The constructor.
   */
  public ApplicationAccessControlConfig() {

    super();
    AccessControlGroup readMasterData = group(GROUP_READ_MASTER_DATA, PERMISSION_FIND_BINARY_OBJECT);
    group(GROUP_ADMIN, readMasterData, PERMISSION_SAVE_BINARY_OBJECT, PERMISSION_DELETE_BINARY_OBJECT);
  }

}