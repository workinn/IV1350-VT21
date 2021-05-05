package se.kth.iv1350.danielhenning.controller;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.model.CashRegister;
import se.kth.iv1350.danielhenning.model.Discount;
import se.kth.iv1350.danielhenning.model.Sale;
import se.kth.iv1350.danielhenning.model.SaleLog;

/**
 * The class Controller represents the interface 
 * of the System Under Development
 */
public class Controller {

  private HandlerCreator handler;
  private CashRegister cashRegister;
  private SaleLog todaysSaleLog;
  private Discount discount;
  private Sale currentSale;

  /**
   * Creates a new instance of the class Controller
   * 
   * @param handler HandlerCreator of the program that cotains the handlers in the integration layer.
   * @param cashRegister is the CashRegister that contains information about the amount of cash in the register.
   */
  public Controller(HandlerCreator handler, CashRegister cashRegister) {
    this.handler = handler;
    this.cashRegister = cashRegister;
    this.todaysSaleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    this.discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
  }

  /**
   * The method startSale starts a new sale. A new sale
   * is started by creating a new instance of the class Sale
   */
  public void startSale() {
    currentSale = new Sale(handler, todaysSaleLog, discount);
  }

  /**
   * The method addItem adds a new item to the Sale 
   * @param itemIdentifier is the identifier (bar code) of the scanned item
   * @return a SaleDTO which represents the current state of the Sale
   */
  public SaleDTO addItem(String itemIdentifier) {
    return currentSale.addItem(itemIdentifier);
  }

  /**
   * The method addQuantity sets the quantity of the last scanned
   * item to the given quantity
   * @param quantity is the quantity to set the last scanned item to
   * @return a SaleDTO which represents the current state of the Sale
   */
  public SaleDTO addQuantity(int quantity) {
    return currentSale.addQuantity(quantity);
  }

  /**
   * The method endSale ends the current Sale
   * @return  a SaleDTO which represents the current state of the Sale
   */
  public SaleDTO endSale() {
    return currentSale.endSale();
  }

  /**
   * The method requestDiscount requests discount for the current sale
   * @param customerID is the identification given by the customer
   * @return a SaleDTO which represents the current state of the Sale
   */
  public SaleDTO requestDiscount(String customerID) {
    return currentSale.addDiscount(customerID);
  }

  /**
   * The method payment adds the payment to the CashRegister as well as
   * prints the receipt of the current sale
   * @param amountPaid is the amount paid by the customer
   * @return a SaleDTO which represents the current state of the Sale
   */
  public SaleDTO payment(int amountPaid) {
    SaleDTO saleDTO = currentSale.logSale();
    cashRegister.addPayment(saleDTO);
    double change = cashRegister.getChange(amountPaid, saleDTO);
    saleDTO = currentSale.printReceipt(amountPaid, change);
    return saleDTO;
  }
}
