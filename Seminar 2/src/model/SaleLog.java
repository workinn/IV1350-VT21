package model;

import DTO.SaleDTO;
import integration.AccountingHandler;
import integration.InventoryHandler;

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
