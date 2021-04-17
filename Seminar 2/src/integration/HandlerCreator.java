package integration;

public class HandlerCreator {

  AccountingHandler accountingHandler;
  InventoryHandler inventoryHandler;
  DiscountHandler discountHandler;
  MemberHandler memberHandler;
  PrinterHandler printerHandler;
  
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
