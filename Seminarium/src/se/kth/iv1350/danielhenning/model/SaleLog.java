package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.AccountingHandler;
import se.kth.iv1350.danielhenning.integration.InventoryHandler;

public class SaleLog {

  private AccountingHandler accountingHandler;
  private InventoryHandler inventoryHandler;

  public SaleLog(AccountingHandler accountingHandler, InventoryHandler inventoryHandler) {
    this.accountingHandler = accountingHandler;
    this.inventoryHandler = inventoryHandler;
  }

  public void logSale(SaleDTO sale) {
    accountingHandler.updateAccounting(sale);
    inventoryHandler.updateInventory(sale);
  }
}
