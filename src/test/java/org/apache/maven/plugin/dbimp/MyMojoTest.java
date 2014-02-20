package org.apache.maven.plugin.dbimp;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.testng.annotations.Test;

/**
 * * @author Mickaël Patron
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

  // protected void setUp() throws Exception {
  // super.setUp();
  // LOGGER.info("MyMojoTest.setUp()");
  // }

  protected void tearDown() throws Exception {
    super.tearDown();
    LOGGER.info("MyMojoTest.tearDown()");
  }

  @Test
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
      LOGGER.severe("!!! AbstractMojoTestCase et JUnit ont quel lien ? !!! Héritage !!! ");
      LOGGER.log(Level.SEVERE, "Erreur dans l'execution du plugin durant sa phase de test.", e);
      assertFalse(true);
    }

  }

}
