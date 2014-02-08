package org.apache.maven.plugin.dbimp;

import java.io.File;
import java.util.logging.Logger;

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

/**
 *  * @author Mickaël Patron
 *
 */
public class MyMojoTest extends AbstractMojoTestCase {
  private static Logger LOGGER = Logger.getLogger(MyMojoTest.class.getName());


  protected static void setUpBeforeClass() throws Exception {
    LOGGER.info("MyMojoTest.setUpBeforeClass()");
  }

  protected static void tearDownAfterClass() throws Exception {
    LOGGER.info("MyMojoTest.tearDownAfterClass()");
  }

//  protected void setUp() throws Exception {
//    super.setUp();
//    LOGGER.info("MyMojoTest.setUp()");
//  }

  protected void tearDown() throws Exception {
    super.tearDown();
    LOGGER.info("MyMojoTest.tearDown()");
  }

  public void testExecute() {
    LOGGER.info("MyMojoTest.testExecute()");
    try {
      File pom = getTestFile("src/test/resources/unit/project-to-test/plugin-config.xml");
      assertNotNull(pom);
      assertTrue(pom.exists());

      Mojo myMojo = lookupMojo("sayhi", pom);
      assertNotNull(myMojo);
      myMojo.execute();
      
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
