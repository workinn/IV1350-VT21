package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.dto.SaleLogDTO;
import se.kth.iv1350.danielhenning.integration.AccountingHandler;
import se.kth.iv1350.danielhenning.integration.InventoryHandler;

/**
 * The SaleLog class represents 
 */
public class SaleLog {

  private AccountingHandler accountingHandler;
  private InventoryHandler inventoryHandler;

  public SaleLog(AccountingHandler accountingHandler, InventoryHandler inventoryHandler) {
    this.accountingHandler = accountingHandler;
    this.inventoryHandler = inventoryHandler;
  }

  public void logSale(SaleDTO sale) {
    SaleLogDTO saleLog = new SaleLogDTO(sale);
    accountingHandler.updateAccounting(saleLog);
    inventoryHandler.updateInventory(saleLog);
  }
}
