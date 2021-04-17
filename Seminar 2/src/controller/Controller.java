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
    SaleDTO saleDTO = currentSale.logSale();
    currentSale.printReciept();
    cashRegister.addPayment(saleDTO);
    return cashRegister.getChange(amountPaid, saleDTO);
  }
}
