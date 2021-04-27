package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.integration.HandlerCreator;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
public class Sale {

  private HandlerCreator handler;
  // private float runningTotal;   // Denna och två under kanske ska vara klass av ItemList
  // private ArrayList<ItemInformationDTO> items;
  // private ItemInformationDTO lastAddedItem;
  private Discount discount;
  private SaleLog saleLog;
  private ItemList items;
  private boolean lastItemFound;

  public Sale(HandlerCreator handler) {
    this.handler = handler;
    // this.runningTotal = 0;
    // this.items = new ArrayList<ItemInformationDTO>();
    // this.lastAddedItem = null;
    this.discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    this.saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    this.items = new ItemList();
  }

  /*
  private void addItemInternally(ItemInformationDTO item, int quantity) {

    boolean itemAdded = false;
    ItemInformationDTO newItem;
    int temp_quantity;

    // Check if item already exists in items
    for(int i = 0; i < items.size(); i++) {
      if(items.get(i) == item) {

        temp_quantity = item.getQuantity();

        newItem = new ItemInformationDTO(item, temp_quantity + quantity, 0);
        items.remove(i);
        items.add(newItem);
        itemAdded = true;
        System.out.println("Added item " + newItem.getItemDescription());
      }
    }

    if(itemAdded == false) {
      items.add(item);
      System.out.println("Added item " + item.getItemDescription());
    }

    runningTotal += (item.getPrice() * quantity);
    lastAddedItem = item;
    System.out.println("Running total = " + runningTotal);
  }*/

  private SaleDTO getSaleDTO() {
    return new SaleDTO(items);
  }

  public SaleDTO addItem(String itemIdentifier) {
    //System.out.println("Hello from addItem!");
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    
    //System.out.println(item.getItemDescription());

    if(item == null) {
      lastItemFound = false;
    } else {
      items.addItem(item);
    }
    return getSaleDTO();
  }

  public SaleDTO addQuantity(int quantity) {
    items.increaseQuantityOfLastScannedItem(quantity);

    return getSaleDTO();
  }

  public SaleDTO endSale() {
    return getSaleDTO();
  }

  public SaleDTO addDiscount(String customerID) {

    System.out.println("Looking for discounts...");

    // Save current state of Sale in DTO
    SaleDTO saleDTO = new SaleDTO(items, runningTotal, true);

    // Get updated SaleDTO from discount
    saleDTO = discount.addDiscount(saleDTO, customerID);

    // Update state of Sale
    items = saleDTO.getItems();
    runningTotal = saleDTO.getRunningTotal();
    
    System.out.println("Running total after discounts = " + runningTotal);

    return saleDTO;
  }

  public SaleDTO logSale() {
    // Save current state of Sale in DTO
    SaleDTO saleDTO = new SaleDTO(items, runningTotal, true);

    // Log sale
    saleLog.logSale(saleDTO);

    return saleDTO;
  }

  public void printReciept(float amountPaid, float change) {
    // Save current state of Sale in DTO
    SaleDTO saleDTO = new SaleDTO(items, runningTotal, true);

    // Create reciept by sending SaleDTO
    Reciept reciept = new Reciept(saleDTO, amountPaid, change);

    // Print reciept
    handler.getPrinterHandler().printRecipt(reciept);
  }

  public void printItems() {
    for(int i = 0; i < items.size(); i++) {
      System.out.println(items.get(i).getItemDescription());
    }
  }
}
