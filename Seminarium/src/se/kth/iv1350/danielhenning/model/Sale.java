package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.integration.InventoryHandler;
import se.kth.iv1350.danielhenning.integration.PrinterHandler;

import java.time.LocalDateTime;

import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.RecieptDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
public class Sale {

  // private HandlerCreator handler;
  private InventoryHandler inventoryHandler;
  private PrinterHandler printer;
  private Discount discount;
  private SaleLog saleLog;
  private ItemList items;
  private boolean lastItemFound;
  private LocalDateTime dateTime;

  public Sale(InventoryHandler inventoryHandler, PrinterHandler printer, SaleLog saleLog, Discount discount) {
    this.inventoryHandler = inventoryHandler;
    this.printer = printer;
    this.discount = discount;
    this.saleLog = saleLog;
    this.items = new ItemList();
    this.dateTime = LocalDateTime.now();
  }

  private SaleDTO getSaleDTO() {
    return new SaleDTO(items, lastItemFound, dateTime); //Skicka hela Sale?
  }

  public SaleDTO addItem(String itemIdentifier) {
    ItemInformationDTO item = inventoryHandler.getItemInformation(itemIdentifier);
    
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

    DiscountDTO discountDTO = discount.addDiscount(getSaleDTO(), customerID);

    items.addDiscount(discountDTO);
    
    return getSaleDTO();
  }

  public SaleDTO logSale() {
    SaleDTO saleDTO = getSaleDTO();
    saleLog.logSale(saleDTO);

    return saleDTO; //? ska denna retuneras?
  }

  public void printReciept(double amountPaid, double change) {

    RecieptDTO reciept = new RecieptDTO(getSaleDTO(), amountPaid, change);
    printer.printRecipt(reciept);
  }
}
