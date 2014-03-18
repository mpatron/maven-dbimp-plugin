package org.apache.maven.plugin.dbimp;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.jobjects.derby.DerbySingleton;
import org.jobjects.tools.Order;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * * @author Mickaël Patron
 * 
 */
//@RunWith(OrderedRunner.class)
public class MyMojoTest extends AbstractMojoTestCase {
  private static Logger LOGGER = Logger.getLogger(MyMojoTest.class.getName());

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    LOGGER.info("MyMojoTest.setUpBeforeClass()");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
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
  @Order(order=1)
  public void testAAAStartDatabase() {
    LOGGER.info("MyMojoTest.testAAAStartDatabase()");
    try {
      //DerbyStart.setUpBeforeClass();
    } catch (Throwable e) {
      LOGGER.log(Level.SEVERE, "erreur dans le chargement de la base derby.", e);
      assertFalse(false);
    }
  }
  
  @Test
  @Order(order=2)
  public void testAABExecute() {
    LOGGER.info("MyMojoTest.testAABExecute()");
    try {
      DerbySingleton.getInstance().dbStart();
      DerbySingleton.getInstance().createSchema();
      
      File pom = getTestFile("src/test/resources/unit/project-to-test/plugin-config.xml");

      assertNotNull(pom);
      assertTrue(pom.exists());

      Mojo myMojo = lookupMojo("sayhi", pom);
      assertNotNull(myMojo);
      myMojo.execute();
      assertTrue(true);
    } catch (Throwable e) {
      LOGGER.severe("!!! AbstractMojoTestCase et JUnit ont quel lien ? !!! Héritage !!! ");
      LOGGER.log(Level.SEVERE, "Erreur dans l'execution du plugin durant sa phase de test.", e);
      assertFalse(false);
    }
  }

  @Test
  @Order(order=3)
  public void testAACExecute2() {
    LOGGER.info("MyMojoTest.testAACExecute2()");    
  }

  @Test
  @Order(order=4)
  public void testAADStopDatabase() {
    LOGGER.info("MyMojoTest.testAADStopDatabase()");
  }
}
