package org.jobjects.tools;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
/*
 * http://stackoverflow.com/questions/3089151/specifying-an-order-to-junit-4-tests-at-the-method-level-not-class-level
 */
@RunWith(OrderedRunner.class)
public class SomethingTest {
  private static Logger LOGGER = Logger.getLogger(SomethingTest.class.getName());
    @Test
    @Order(order=2)
    public void testUpdateArticle() {
      LOGGER.info("SomethingTest.testUpdateArticle()");
    }

    @Test
    @Order(order=1)
    public void testInsertArticle() {
      LOGGER.info("SomethingTest.testInsertArticle()");
    }

    @Test
    @Order(order=3)
    public void testDeleteArticle() {
      LOGGER.info("SomethingTest.testDeleteArticle()");
    }
}