package se.kth.iv1350.danielhenning.dto;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.model.ItemList;

public class SaleDTO {

  ArrayList<ItemInformationDTO> items;
  float runningTotal;
  boolean lastItemFound;

  public SaleDTO(ItemList items) {
    this.items = items.getItems();
    this.runningTotal = items.getRunningTotal();
    this.lastItemFound = items.getLastItemFound();
  }

  public ArrayList<ItemInformationDTO> getItems() {
    return items;
  }

  public float getRunningTotal() {
    return runningTotal;
  }

  public boolean getLastItemFound() {
    return lastItemFound;
  }
}
