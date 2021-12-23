package br.com.artur;

import org.testcontainers.containers.MySQLContainer;

public class TelzirContainer extends MySQLContainer<TelzirContainer> {

  public static final String MYSQL_VERSION = "mysql:5.7";
  public static final String APP_NAME = "offnance";

  private boolean isActive;

  public static TelzirContainer container;

  public static TelzirContainer getInstance() {
    return getInstance(true);
  }

  public static TelzirContainer getInstance(final boolean isActive) {
    if (container == null) {
      container = new TelzirContainer(isActive);
    }
    return container;
  }

  public TelzirContainer(final boolean isActive) {
    super(MYSQL_VERSION);
    this.withUsername(APP_NAME).withDatabaseName(APP_NAME).withPassword(APP_NAME)
        .isActive(isActive).withReuse(false);
  }

  private TelzirContainer isActive(boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  @Override
  public void start() {
    if (this.isActive) {
      super.start();
      System.setProperty("DB_URL", container.getJdbcUrl());
      System.setProperty("DB_USR", container.getUsername());
      System.setProperty("DB_PASS", container.getPassword());
    }
  }

  @Override
  public void stop() {
  }
}
