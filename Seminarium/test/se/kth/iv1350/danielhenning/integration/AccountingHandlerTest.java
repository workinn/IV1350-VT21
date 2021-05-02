package se.kth.iv1350.danielhenning.integration;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;


public class AccountingHandlerTest {

  private AccountingHandler accountingHandler;

  public void startUp() {
    accountingHandler = new AccountingHandler();
  }

  public void tearDown() {
    accountingHandler = null;
  }

  @Test
  public void testAccountingHandler() {
    startUp();
    assertTrue(accountingHandler!=null);
    tearDown();
  }
}
