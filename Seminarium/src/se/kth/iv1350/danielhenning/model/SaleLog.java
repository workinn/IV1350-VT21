package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.AccountingHandler;
import se.kth.iv1350.danielhenning.integration.InventoryHandler;

/**
 * The SaleLog class represents 
 */
public class SaleLog {

  private AccountingHandler accountingHandler;
  private InventoryHandler inventoryHandler;
  private ArrayList<SaleDTO> todaysSales;
  private double amountSoldForToday;
//Discount given?
  public SaleLog(AccountingHandler accountingHandler, InventoryHandler inventoryHandler) {
    this.accountingHandler = accountingHandler;
    this.inventoryHandler = inventoryHandler;
    this.todaysSales = new ArrayList<SaleDTO>();
    this.amountSoldForToday = 0;
  }

  public void logSale(SaleDTO saleDTO) {
    //SaleLogDTO saleLog = new SaleLogDTO(sale);
    todaysSales.add(saleDTO);
    amountSoldForToday += saleDTO.getRunningTotal();
    accountingHandler.updateAccounting(saleDTO);
    inventoryHandler.updateInventory(saleDTO);
  }

  public ArrayList<SaleDTO> getTodaysSales() {
    return todaysSales;
  }

  public double getAoumtSoldForToday() {
    return amountSoldForToday;
  }
}
