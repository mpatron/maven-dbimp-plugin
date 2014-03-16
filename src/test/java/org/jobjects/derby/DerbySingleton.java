package org.jobjects.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DerbySingleton {

  private static Logger LOGGER = Logger.getLogger(DerbySingleton.class.getName());

  private static DerbySingleton instance = null;

  private DerbySingleton() {
    try {
      Class.forName(DerbyConstantes.DRIVER_CLASSNAME);
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Erreur non prévu : ", e);
    }
  }

  public static DerbySingleton getInstance() {
    if (null == instance) {
      instance = new DerbySingleton();
    }
    return instance;
  }

  /**
   * @throws java.lang.Exception
   */
  public void dbStart() throws Exception {
    try {
      LOGGER.log(Level.INFO, "Derby Starting...");

      Class.forName(DerbyConstantes.DRIVER_CLASSNAME).newInstance();
      Properties p = new Properties();
      p.setProperty(DerbyConstantes.USER, DerbyConstantes.USER_VALUE);
      p.setProperty(DerbyConstantes.PASSWORD, DerbyConstantes.PASSWORD_VALUE);
      p.setProperty("create", "true");
      Connection conn = DriverManager.getConnection(DerbyConstantes.URL, p);

      Properties p2 = new Properties();
      p2.setProperty(DerbyConstantes.USER, DerbyConstantes.USER_VALUE);
      p2.setProperty(DerbyConstantes.PASSWORD, DerbyConstantes.PASSWORD_VALUE);
      Connection conn2 = DriverManager.getConnection(DerbyConstantes.URL, p2);
      conn2.close();

      CreateSchema.createSchema(conn, DerbyConstantes.SCHEMA_NAME);
      conn.close();

      LOGGER.log(Level.INFO, "Derby Started");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Erreur non prévu : ", e);
    }
  }

  public void dbStop() throws Exception {
    LOGGER.log(Level.INFO, "Derby Stoping...");
    try {
      LOGGER.info("Extinction de Derby");
      DriverManager.getConnection(DerbyConstantes.URL + ";shutdown=true");
    } catch (Exception ignored) {
      LOGGER.log(Level.INFO, "Extinction de " + DerbyConstantes.URL + " : " + ignored.getLocalizedMessage());
    }
    try {
      LOGGER.info("Extinction de Derby");
      DriverManager.getConnection("jdbc:derby:;shutdown=true");
    } catch (Exception ignored) {
      LOGGER.log(Level.INFO, "Extinction de derby : " + ignored.getLocalizedMessage());
    }
    LOGGER.log(Level.INFO, "Derby Stoped.");
  }

  public Connection getConnection() throws SQLException {
    Properties p = new Properties();
    p.setProperty(DerbyConstantes.USER, DerbyConstantes.USER_VALUE);
    p.setProperty(DerbyConstantes.PASSWORD, DerbyConstantes.PASSWORD_VALUE);
    p.setProperty("create", "true");// +";create=true"    
    return DriverManager.getConnection(DerbyConstantes.URL, p);
  }

  public void createSchema() {
    try {
      Connection conn = getConnection();

      Statement stmp = conn.createStatement();
      try {
        stmp.execute("create schema " + DerbyConstantes.SCHEMA_NAME + " AUTHORIZATION " + DerbyConstantes.USER_VALUE);
      } catch (Exception e) {
        LOGGER.log(Level.WARNING, e.getMessage());
      } finally {
        stmp.close();
      }
      CreateSchema.createSchema(conn, DerbyConstantes.SCHEMA_NAME);
      conn.close();
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Erreur non prévu : ", e);
    }
  }

}
