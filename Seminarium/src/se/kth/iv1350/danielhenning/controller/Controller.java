package se.kth.iv1350.danielhenning.controller;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.model.CashRegister;
import se.kth.iv1350.danielhenning.model.Discount;
import se.kth.iv1350.danielhenning.model.Sale;
import se.kth.iv1350.danielhenning.model.SaleLog;

/**
 * Represents ...... Instances are immutable(?).
 */
public class Controller {

  private HandlerCreator handler;
  private CashRegister cashRegister;
  private SaleLog todaysSaleLog;
  private Discount todaysDiscount;
  private Sale currentSale;

  /**
   * Creates a new instance, representing the controller.
   * 
   * @param handler HandlerCreator of the program that cotains the handlers in the integration layer.
   * @param cashRegister is the CashRegister that contains information about the amount of cash in the register.
   */
  public Controller(HandlerCreator handler, CashRegister cashRegister) {
    this.handler = handler;
    this.cashRegister = cashRegister;
    this.todaysSaleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    this.todaysDiscount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
  }

  /**
   * Starts a new sale, creates a new instance of the sale class.
   */
  public void startSale() {
    currentSale = new Sale(handler.getInventoryHandler(), handler.getPrinterHandler(), todaysSaleLog, todaysDiscount);
  }

  /**
   * 
   */
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
  public double payment(int amountPaid) {
    System.out.println("Amount paid = " + amountPaid);
    SaleDTO saleDTO = currentSale.logSale();
    cashRegister.addPayment(saleDTO);
    double change = cashRegister.getChange(amountPaid, saleDTO);
    System.out.println("Change = " + change);
    currentSale.printReciept(amountPaid, change);
    return change;
  }
}
