package src.model;

import src.DTO.SaleDTO;
import src.integration.AccountingHandler;
import src.integration.InventoryHandler;

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
