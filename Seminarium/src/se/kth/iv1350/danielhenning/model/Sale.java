package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.integration.InventoryHandler;
import se.kth.iv1350.danielhenning.integration.PrinterHandler;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.RecieptDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

import java.time.LocalDateTime;

public class Sale {
  private InventoryHandler inventoryHandler;
  private PrinterHandler printer;
  private Discount discount;
  private SaleLog saleLog;
  private ItemList items;
  private boolean lastItemFound;
  private LocalDateTime dateTime;
  private double runningTotal;
  private double discountOnWholeSale;
  private double amountPaid;
  private double change;

  public Sale(InventoryHandler inventoryHandler, PrinterHandler printer, SaleLog saleLog, Discount discount) {
    this.inventoryHandler = inventoryHandler;
    this.printer = printer;
    this.discount = discount;
    this.saleLog = saleLog;
    this.items = new ItemList();
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

  public SaleDTO addItem(String itemIdentifier) {
    ItemInformationDTO item = inventoryHandler.getItemInformation(itemIdentifier);
    
    if(item == null) {
      lastItemFound = false;
    } else {
      lastItemFound = true;
      items.addItem(item);
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

    DiscountDTO discountDTO = discount.addDiscount(getSaleDTO(), customerID);
    discountOnWholeSale = discountDTO.getTotalSaleDiscount();

    items.addDiscount(discountDTO);
    
    return getSaleDTO();
  }

  public SaleDTO logSale() {
    SaleDTO saleDTO = getSaleDTO();
    saleLog.logSale(saleDTO);

    return saleDTO;
  }

  public SaleDTO printReciept(double amountPaid, double change) {
    this.amountPaid = amountPaid;
    this.change = change;
    RecieptDTO reciept = new RecieptDTO(getSaleDTO(), amountPaid, change);
    printer.printRecipt(reciept);
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
