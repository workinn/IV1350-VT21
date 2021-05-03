package se.kth.iv1350.danielhenning.integration;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AccountingHandlerTest {

  private AccountingHandler accountingHandler;

  @BeforeEach
  public void startUp() {
    System.out.println("Start up!");
    accountingHandler = new AccountingHandler();
  }

  @AfterEach
  public void tearDown() {
    System.out.println("Tear down!");
    accountingHandler = null;
  }

  @Test
  public void testAccountingHandler() {
    System.out.println("Testing AccountingHandler!");
    assertTrue(accountingHandler!=null);
  }
}
