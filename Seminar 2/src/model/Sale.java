package model;

import DTO.ItemInformationDTO;
import DTO.SaleDTO;
import integration.HandlerCreator;

import java.util.ArrayList;
public class Sale {

  private HandlerCreator handler;
  private float runningTotal;
  private ArrayList<ItemInformationDTO> items;
  private ItemInformationDTO lastAddedItem;
  private Discount discount;
  private SaleLog saleLog;

  public Sale(HandlerCreator handler) {
    this.handler = handler;
    this.runningTotal = 0;
    this.items = new ArrayList<ItemInformationDTO>();
    this.lastAddedItem = null;
    this.discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    this.saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
  }

  private void addItemInternally(ItemInformationDTO item, int quantity) {

    boolean itemAdded = false;

    // Check if item already exists in items
    for(int i = 0; i < items.size(); i++) {
      if(items.get(i) == item) {

        int temp_quantity = item.getQuantity();

        ItemInformationDTO newItem = new ItemInformationDTO(item, temp_quantity + quantity);
        items.remove(i);
        items.add(newItem);
        itemAdded = true;
      }
    }

    if(itemAdded == false) {
      items.add(item);
    }

    runningTotal += (item.getPrice() * quantity);
    lastAddedItem = item;
  }

  public SaleDTO addItem(String itemIdentifier) {
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    
    if(item != null) {
      addItemInternally(item, 1);
      SaleDTO saleDTO = new SaleDTO(items, runningTotal, true);
      return saleDTO;
    } else {
      SaleDTO saleDTO = new SaleDTO(items, runningTotal, false);
      return saleDTO;
    }

  }

  public SaleDTO addQuantity(int quantity) {
    addItemInternally(lastAddedItem, quantity - 1);

    // Save current state of Sale in DTO
    SaleDTO saleDTO = new SaleDTO(items, runningTotal, true);
    return saleDTO;
  }

  public SaleDTO endSale() {
    // Save current state of Sale in DTO
    SaleDTO saleDTO = new SaleDTO(items, runningTotal, true);
    return saleDTO;
  }

  public SaleDTO addDiscount(String customerID) {
    // Save current state of Sale in DTO
    SaleDTO saleDTO = new SaleDTO(items, runningTotal, true);

    // Get updated SaleDTO from discount
    saleDTO = discount.addDiscount(saleDTO, customerID);

    // Update state of Sale
    items = saleDTO.getItems();
    runningTotal = saleDTO.getRunningTotal();

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
}
