package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.integration.HandlerCreator;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
public class Sale {

  private HandlerCreator handler;
  private Discount discount;
  private SaleLog saleLog;
  private ItemList items;
  private boolean lastItemFound;

  public Sale(HandlerCreator handler) {
    this.handler = handler;
    this.discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    this.saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    this.items = new ItemList();

    System.out.println("Start Sale!");
  }

  private SaleDTO getSaleDTO() {
    return new SaleDTO(items, lastItemFound);
  }

  public SaleDTO addItem(String itemIdentifier) {
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    
    if(item == null) {
      lastItemFound = false;
    } else {
      lastItemFound = true;
      items.addItem(item);

      System.out.println("Item added: " + item.getItemDescription());
      System.out.println("Running Total: " + items.getRunningTotal());
    }

    return getSaleDTO();
  }

  public SaleDTO addQuantity(int quantity) {
    items.increaseQuantityOfLastScannedItem(quantity - 1);

    return getSaleDTO();
  }

  public SaleDTO endSale() {
    return getSaleDTO();
  }

  public SaleDTO addDiscount(String customerID) {

    System.out.println("Looking for discounts...");

    SaleDTO saleDTO = discount.addDiscount(getSaleDTO(), customerID);

    // Update state of Sale
    items.addDiscount(saleDTO);
    
    // System.out.println("Running total after discounts = " + items.getRunningTotal();

    return saleDTO;
  }

  public SaleDTO logSale() {

    SaleDTO saleDTO = getSaleDTO();
    // Log sale
    saleLog.logSale(saleDTO);

    return saleDTO;
  }

  public void printReciept(float amountPaid, float change) {

    // Create reciept by sending SaleDTO
    Reciept reciept = new Reciept(getSaleDTO(), amountPaid, change);

    // Print reciept
    handler.getPrinterHandler().printRecipt(reciept);
  }

}
