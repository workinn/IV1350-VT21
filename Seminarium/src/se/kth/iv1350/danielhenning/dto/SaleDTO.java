package se.kth.iv1350.danielhenning.dto;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.model.ItemList;

public class SaleDTO {

  ArrayList<ItemRowDTO> itemRows;
  boolean lastItemFound;
  float runningTotal;
  int numberOfItems;

  public SaleDTO(ItemList itemList, boolean lastItemFound) {
    itemRows = new ArrayList<ItemRowDTO>();
    for(int i = 0; i < itemList.getItemList().size(); i++) {
      this.itemRows.add(new ItemRowDTO(itemList.getItemList().get(i)));
    }
    this.lastItemFound = lastItemFound;
    this.runningTotal = itemList.getRunningTotal();
    this.numberOfItems = itemList.getNumberOfItems();
  }

  public ArrayList<ItemRowDTO> getItemRows() {
    return itemRows;
  }

  public float getRunningTotal() {
    return runningTotal;
  }

  public int getNumberOfItems() {
    return numberOfItems;
  }

  public boolean getLastItemFound() {
    return lastItemFound;
  }
}
