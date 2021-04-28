package se.kth.iv1350.danielhenning.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.kth.iv1350.danielhenning.model.ItemList;

public class SaleDTO {

  private ArrayList<ItemRowDTO> itemRows;
  private boolean lastItemFound;
  private double runningTotal;
  private int numberOfItems;
  private LocalDateTime dateTime;

  public SaleDTO(ItemList itemList, boolean lastItemFound, LocalDateTime dateTime) {
    itemRows = new ArrayList<ItemRowDTO>();
    // System.out.println(itemList.getItemList().size());
    for(int i = 0; i < itemList.getItemList().size(); i++) {
      // System.out.println(i);
      this.itemRows.add(new ItemRowDTO(itemList.getItemList().get(i)));
    }
    this.lastItemFound = lastItemFound;
    this.runningTotal = itemList.getRunningTotal();
    this.numberOfItems = itemList.getNumberOfItems();
    this.dateTime = dateTime;
    // System.out.println("SaleDTO created!");
  }

  public ArrayList<ItemRowDTO> getItemRows() {
    return itemRows;
  }

  public double getRunningTotal() {
    return runningTotal;
  }

  public int getNumberOfItems() {
    return numberOfItems;
  }

  public boolean getLastItemFound() {
    return lastItemFound;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }
}
