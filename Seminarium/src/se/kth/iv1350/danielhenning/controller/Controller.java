package se.kth.iv1350.danielhenning.controller;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.model.CashRegister;
import se.kth.iv1350.danielhenning.model.Sale;

/**
 * Represents ...... Instances are immutable(?).
 */
public class Controller {

  private HandlerCreator handler;
  private CashRegister cashRegister;
  private Sale currentSale;

  /**
   * Creates a new instance, representing the controller.
   * 
   * @param handler
   * @param cashRegister
   */
  public Controller(HandlerCreator handler, CashRegister cashRegister) {
    this.handler = handler;
    this.cashRegister = cashRegister;
  }

  /**
   * 
   */
  public void startSale() {
    currentSale = new Sale(handler);
  }

  public SaleDTO addItem(String itemIdentifier) {
    return currentSale.addItem(itemIdentifier);
  }

  public SaleDTO addQuantity(int quantity) {
    return currentSale.addQuantity(quantity);
  }

  /**
   * 
   * @return 
   */
  public SaleDTO endSale() {
    return currentSale.endSale();
  }

  public SaleDTO requestDiscount(String customerID) {
    return currentSale.addDiscount(customerID);
  }

  /**
   * 
   * @param amountPaid
   */
  public float payment(int amountPaid) {
    System.out.println("Amount paid = " + amountPaid);
    SaleDTO saleDTO = currentSale.logSale();
    cashRegister.addPayment(saleDTO);
    float change = cashRegister.getChange(amountPaid, saleDTO);
    System.out.println("Change = " + change);
    currentSale.printReciept(amountPaid, change);
    return change;
  }
}
