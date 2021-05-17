package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.integration.CouldNotConnectToServerException;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.integration.ItemDoesNotExistException;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.ReceiptDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;


import java.time.LocalDateTime;
import java.util.concurrent.RejectedExecutionException;

/**
 * The Sale class represents the whole sale. It has general information
 * about the sale such as running total, discount on whole sale, amount
 * paid, change, time and date. It also has the handler to manage 
 * communication with the integration layer. The variable lastItemFound
 * is used as a flag for the View to check if a scanned item was found
 * by the External Inventory System
 */
public class Sale {
  private HandlerCreator handler;
  private SaleLog saleLog;
  private ItemList items;
  private boolean lastItemFound;
  private LocalDateTime dateTime;
  private double runningTotal;
  private double discountOnWholeSale;
  private double amountPaid;
  private double change;
  private Discount discount;

  /**
   * Creates a new instance of the class Sale
   * @param handler contains all the handlers in the integration layer
   * @param saleLog is used to log the completed sale
   */
  public Sale(HandlerCreator handler, SaleLog saleLog, Discount discount) {
    this.handler = handler;
    this.saleLog = saleLog;
    this.items = new ItemList();
    this.discount = discount;
    this.dateTime = LocalDateTime.now();
    this.runningTotal = 0;
    this.discountOnWholeSale = 0;
    this.amountPaid = 0;
    this.change = 0;
  }

  private SaleDTO getSaleDTO() {
    calculateRunningTotal();
    return new SaleDTO(this);
  }

  private void calculateRunningTotal() {
    runningTotal = 0;
    for(int i = 0; i < items.getItemRows().size(); i++) {
      runningTotal += items.getItemRows().get(i).getPriceIncludingDiscount();
    }
    runningTotal -= discountOnWholeSale;
  }

  /**
   * The method addItem gets the item from an External 
   * Inventory System and adds the item to ItemList
   * @param itemIdentifier is the identifier (bar code) of scanned item
   * @return a SaleDTO for the View to retreive information about the current state of the Sale
   */
  public SaleDTO addItem(String itemIdentifier) throws ItemDoesNotExistException, CouldNotConnectToServerException{
    try{
      ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
      items.addItem(item);
      lastItemFound = true;
    }catch(ItemDoesNotExistException exc){
      lastItemFound = false;
      throw exc;
    }catch(CouldNotConnectToServerException exc){
      lastItemFound = false;
      throw exc;
    }
    return getSaleDTO();
  }

  /**
   * The method addQuantity sets the quantity of the 
   * last scanned item to the given quantity
   * @param quantity the quantity of items of the same type as the last scanned one
   * @return a SaleDTO for the View to retreive information about the current state of the Sale
   */
  public SaleDTO addQuantity(int quantity) {
    if(lastItemFound && quantity > 0) {
      items.increaseQuantityOfLastScannedItem(quantity - 1);
    }

    return getSaleDTO();
  }

  /**
   * Ends the current sale. In this implementation, no functionality is
   * added but that might change in the future
   * @return a SaleDTO for the View to retreive information about the current state of the Sale
   */
  public SaleDTO endSale() {
    return getSaleDTO();
  }

  /**
   * The method addDiscount adds discount to the sale
   * @param customerID is the identification given by the customer
   * @return a SaleDTO for the View to retreive information about the current state of the Sale
   */
  public SaleDTO addDiscount(String customerID) {
   DiscountDTO discountDTO = discount.getDiscountDTO(customerID, getSaleDTO());
   discountOnWholeSale = discountDTO.getTotalSaleDiscount();
   items.addDiscount(discountDTO);

    return getSaleDTO();
  }

  /**
   * The method logSale logs the sale in the SaleLog which in turn will
   * send the relevant information of the sale to the External Accounting
   * System and the External Inventory System
   * @return a SaleDTO of the current state of Sale for the controller to send to the CashRegister
   */
  public SaleDTO logSale() {
    SaleDTO saleDTO = getSaleDTO();
    saleLog.logSale(saleDTO);

    return saleDTO;
  }

  /**
   * The method printReceipt creates a ReceiptDTO and sends 
   * it to the PrinterHandler for printing
   * @param amountPaid is the amount paid from the customer
   * @param change is the change to give back to the customer
   * @return a SaleDTO for the View to retreive information about the current state of the Sale
   */
  public SaleDTO printReceipt(double amountPaid, double change) {
    this.amountPaid = amountPaid;
    this.change = change;
    ReceiptDTO receipt = new ReceiptDTO(getSaleDTO());
    handler.getPrinterHandler().printReceipt(receipt);
    return getSaleDTO();
  }

  public ItemList getItemList() {
    return items;
  }

  public boolean getLastItemFound() {
    return lastItemFound;
  }

  public double getRunningTotal() {
    return runningTotal;
  }

  public double getDiscountOnWholeSale() {
    return discountOnWholeSale;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public double getAmountPaid() {
    return amountPaid;
  }

  public double getChange() {
    return change;
  }
}
