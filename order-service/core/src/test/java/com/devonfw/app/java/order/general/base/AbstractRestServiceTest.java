package com.devonfw.app.java.order.general.base;

import com.devonfw.app.java.order.SpringBootApp;
import com.devonfw.app.java.order.general.common.base.test.DbTestHelper;
import com.devonfw.app.java.order.general.service.impl.rest.RestTestConfig;
import com.devonfw.module.test.common.base.SubsystemTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import javax.inject.Inject;

/**
 * Abstract base class for {@link SubsystemTest}s which runs the tests within a local server. <br/>
 * <br/>
 * The local server's port is randomly assigned.
 *
 */

@SpringBootTest(classes = { RestTestConfig.class,
SpringBootApp.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractRestServiceTest extends SubsystemTest {


  /**
   * The port of the web server during the test.
   */
  @LocalServerPort
  protected int port;


  @Inject
  private DbTestHelper dbTestHelper;

  /**
   * Sets up the test.
   */
  @Override
  protected void doSetUp() {

    super.doSetUp();
  }

  /**
   * Cleans up the test.
   */
  @Override
  protected void doTearDown() {

    super.doTearDown();
  }

  /**
   * @return the {@link DbTestHelper}
   */
  public DbTestHelper getDbTestHelper() {

    return this.dbTestHelper;
  }

}
