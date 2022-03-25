package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.AccountingHandler;
import se.kth.iv1350.danielhenning.integration.InventoryHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * The SaleLog class represents the logging of all the
 * sales done today. It stores all completed sales
 * in a list of SaleDTOs and also keep track of
 * the total amount sold for today as well as the 
 * total amount of discount given today. Furthermore
 * the class also manages the communication for storing
 * the completed sales in the External Accounting System
 * as well as updating the External Inventory System
 */
public class SaleLog {

  private AccountingHandler accountingHandler;
  private InventoryHandler inventoryHandler;
  private ArrayList<SaleDTO> todaysSales;
  private double amountSoldForToday;
  private double amountDiscountGivenToday;
  private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<>();
  /**
   * Creates a new instance of the class SaleLog
   * @param accountingHandler is the handler of the accounting
   * @param inventoryHandler is the handler of the inventory
   */
  public SaleLog(AccountingHandler accountingHandler, InventoryHandler inventoryHandler) {
    this.accountingHandler = accountingHandler;
    this.inventoryHandler = inventoryHandler;
    this.todaysSales = new ArrayList<SaleDTO>();
    this.amountSoldForToday = 0;
  }

  private void updateTotalDiscountToday(SaleDTO saleDTO) {
     for(int i = 0; i < saleDTO.getItemRows().size(); i++) {
        amountDiscountGivenToday += saleDTO.getItemRows().get(i).getDiscount();
     }
     amountDiscountGivenToday += saleDTO.getDiscountOnWholeSale();
  }

  /**
   * The method logSale logs the given sale internally
   * as well as sends it to the External Accounting
   * System and the External Inventory System for updating
   * @param saleDTO is the sale to be logged
   */
  public void logSale(SaleDTO saleDTO) {
    if(saleDTO.getItemRows().size() != 0) {
      todaysSales.add(saleDTO);
      amountSoldForToday += saleDTO.getRunningTotal();
      updateTotalDiscountToday(saleDTO);
      accountingHandler.updateAccounting(saleDTO);
      inventoryHandler.updateInventory(saleDTO);
      notifyObservers();
    }
  }

  public ArrayList<SaleDTO> getTodaysSales() {
    return todaysSales;
  }

  public double getAoumtSoldForToday() {
    return amountSoldForToday;
  }

  public double getAmountDiscountGivenToday() {
    return amountDiscountGivenToday;
  }

  private void notifyObservers() {
    for (TotalRevenueObserver obs : totalRevenueObservers) {
      obs.updateTotalSaleAmount(amountSoldForToday);
    }
}

  public void addTotalRevenueObeserver(TotalRevenueObserver obs) {
    totalRevenueObservers.add(obs);
  }

}
