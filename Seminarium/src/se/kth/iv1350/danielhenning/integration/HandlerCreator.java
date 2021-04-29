package se.kth.iv1350.danielhenning.integration;
/**
 * The HandlerCreator class represents a creator
 * and holder of all handlers in the integration layer
 */
public class HandlerCreator {

  AccountingHandler accountingHandler;
  InventoryHandler inventoryHandler;
  DiscountHandler discountHandler;
  MemberHandler memberHandler;
  PrinterHandler printerHandler;
  
  /**
   * Creates a new instance of the class HandlerCreator
   */
  public HandlerCreator() {
    this.accountingHandler = new AccountingHandler();
    this.inventoryHandler = new InventoryHandler();
    this.discountHandler = new DiscountHandler();
    this.memberHandler = new MemberHandler();
    this.printerHandler = new PrinterHandler();
  }

  public AccountingHandler getAccountingHandler() {
    return accountingHandler;
  }

  public InventoryHandler getInventoryHandler() {
    return inventoryHandler;
  }

  public DiscountHandler getDiscountHandler() {
    return discountHandler;
  }

  public MemberHandler getMemberHandler() {
    return memberHandler;
  }

  public PrinterHandler getPrinterHandler() {
    return printerHandler;
  }
}
