package se.kth.iv1350.danielhenning.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.CouldNotConnectToServerException;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.integration.ItemDoesNotExistException;
import se.kth.iv1350.danielhenning.model.CashRegister;
import se.kth.iv1350.danielhenning.model.Sale;
import se.kth.iv1350.danielhenning.model.SaleLog;
import se.kth.iv1350.danielhenning.model.TotalRevenueObserver;
import se.kth.iv1350.danielhenning.util.ExceptionLogger;

/**
 * The class Controller represents the interface 
 * of the System Under Development
 */
public class Controller {

  private HandlerCreator handler;
  private CashRegister cashRegister;
  private SaleLog todaysSaleLog;
  //private Discount discount;
  private Sale currentSale;
  private ExceptionLogger exceptionLogger;
  private List<TotalRevenueObserver> totalRevenueObservers = new ArrayList<>();

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
    exceptionLogger = new ExceptionLogger();
  }

  /**
   * The method startSale starts a new sale. A new sale
   * is started by creating a new instance of the class Sale
   */
  public void startSale() {
    currentSale = new Sale(handler, todaysSaleLog);
  }

  /**
   * The method addItem adds a new item to the Sale 
   * @param itemIdentifier is the identifier (bar code) of the scanned item
   * @return a SaleDTO which represents the current state of the Sale
   * @throws ItemDoesNotExistException catch the exception when the searched item was notfound and throw a new ItemNotFoundException to calling method.
   * @throws CouldNotConnectToServerException catch the exception when connection lost to database and throw a new ItemNotFoundException to calling method.
   * @throws ItemNotFoundException thows this exception to the calling method when any of the above excetions are caught.
   */
  public SaleDTO addItem(String itemIdentifier) throws ItemDoesNotExistException, CouldNotConnectToServerException, ItemNotFoundException{
    try {
      return currentSale.addItem(itemIdentifier); 
    } catch (ItemDoesNotExistException exc) {
      exceptionLogger.logException(exc);
      throw new ItemNotFoundException("Item: "+itemIdentifier+" Not Found");
    }catch(CouldNotConnectToServerException exc){
      exceptionLogger.logException(exc);
      throw new ItemNotFoundException("Item: "+itemIdentifier+" not found, try again");
    }
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

  public void addTotalRevenueObeserver(TotalRevenueObserver obs){
    totalRevenueObservers.add(obs);
    todaysSaleLog.addTotalRevenueObeserver(obs);

  }
}
