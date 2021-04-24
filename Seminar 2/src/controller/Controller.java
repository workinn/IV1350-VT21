package controller;

import DTO.SaleDTO;
import integration.HandlerCreator;
import model.CashRegister;
import model.Sale;

public class Controller {

  private HandlerCreator handler;
  private CashRegister cashRegister;
  private Sale currentSale;

  public Controller(HandlerCreator handler, CashRegister cashRegister) {
    this.handler = handler;
    this.cashRegister = cashRegister;
  }

  public void startSale() {
    currentSale = new Sale(handler);
  }

  public SaleDTO addItem(String itemIdentifier) {
    return currentSale.addItem(itemIdentifier);
  }

  public SaleDTO addQuantity(int quantity) {
    return currentSale.addQuantity(quantity);
  }

  public SaleDTO endSale() {
    return currentSale.endSale();
  }

  public SaleDTO requestDiscount(String customerID) {
    return currentSale.addDiscount(customerID);
  }

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
