package se.kth.iv1350.danielhenning.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AccountingHandlerTest {

  private AccountingHandler accountingHandler;

  @BeforeEach
  public void startUp() {
    accountingHandler = new AccountingHandler();
  }

  @AfterEach
  public void tearDown() {
    accountingHandler = null;
  }

  @Test
  public void testAccountingHandler() {
    boolean expected = true;
    boolean actual = accountingHandler!=null;
    assertEquals(expected, actual, "The AccountingHandler is not initiated!");
  }
}
