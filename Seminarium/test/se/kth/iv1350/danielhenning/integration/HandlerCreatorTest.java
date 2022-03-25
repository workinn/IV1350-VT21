package se.kth.iv1350.danielhenning.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandlerCreatorTest {

  private HandlerCreator handler;

  @BeforeEach
  public void startUp() {
    handler = new HandlerCreator();
  }

  @AfterEach
  public void tearDown() {
    handler = null;
  }

  @Test
  public void testGetAccountingHandler() {
    AccountingHandler accountingHandler = new AccountingHandler();
    boolean expected = true;
    boolean actual = accountingHandler.getClass().equals(handler.getAccountingHandler().getClass());
    assertEquals(expected, actual, "The AccountingHandler from HandlerCreator is not of type AccountingHandler!");
  }

  @Test
  public void testGetDiscountHandler() {
    DiscountHandler discountHandler = new DiscountHandler();
    boolean expected = true;
    boolean actual = discountHandler.getClass().equals(handler.getDiscountHandler().getClass());
    assertEquals(expected, actual, "The DiscountHandler from HandlerCreator is not of type DiscountHandler!");
  }

  @Test
  public void testGetInventoryHandler() {
    InventoryHandler inventoryHandler = InventoryHandler.getInventoryHandler();
    boolean expected = true;
    boolean actual = inventoryHandler.getClass().equals(handler.getInventoryHandler().getClass());
    assertEquals(expected, actual, "The InventoryHandler from HandlerCreator is not of type InventoryHandler!");
  }

  @Test
  public void testGetMemberHandler() {
    MemberHandler memberHandler = new MemberHandler();
    boolean expected = true;
    boolean actual = memberHandler.getClass().equals(handler.getMemberHandler().getClass());
    assertEquals(expected, actual, "The MemberHandler from HandlerCreator is not of type MemberHandler!");
  }

  @Test
  public void testGetPrinterHandler() {
    PrinterHandler printerHandler = new PrinterHandler();
    boolean expected = true;
    boolean actual = printerHandler.getClass().equals(handler.getPrinterHandler().getClass());
    assertEquals(expected, actual, "The PrinterHandler from HandlerCreator is not of type PrinterHandler!");
  }
  
}
