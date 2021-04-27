package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

public class ItemList {

  private ArrayList<RowOfItem> itemList;
  private float runningTotal;
  private int numberOfItems;
  private int numberOfRows;
  private int indexOfLastChangedRow;

  public ItemList() {
    this.itemList = new ArrayList<RowOfItem>();
    this.runningTotal = 0;
    this.numberOfItems = 0;
    this.numberOfRows = 0;
    this.indexOfLastChangedRow = 0;
  }

  private int indexOfRow(ItemInformationDTO item) {
    ItemInformationDTO itemInRow;
    for(int i = 0; i < itemList.size(); i++) {
      itemInRow = itemList.get(i).getItem();
      if(itemInRow == item) {
        return i;
      }
    }
    return -1;
  }

  private void increaseQuantity(int rowIndex, int quantity) {
    itemList.get(rowIndex).increaseQuantity(quantity);
  }

  public void increaseQuantityOfLastScannedItem(int quantity) {
    increaseQuantity(indexOfLastChangedRow, quantity);

    RowOfItem lastChangedRow = itemList.get(indexOfLastChangedRow);

    runningTotal += (lastChangedRow.getPrice() * quantity);
    numberOfItems += quantity;
  }

  public void addItem(ItemInformationDTO item) {
    int notFound = -1;
    int rowIndex = indexOfRow(item);

    if(rowIndex == notFound) {
      RowOfItem newRow = new RowOfItem(item);
      itemList.add(newRow);
      numberOfRows++;
      indexOfLastChangedRow = itemList.size() - 1;
    } else {
      increaseQuantity(rowIndex, 1);
      indexOfLastChangedRow = rowIndex;
    }

    runningTotal += item.getPrice();
    numberOfItems += item.getQuantity();
    System.out.println("Running total = " + runningTotal);
  }

  public ArrayList<RowOfItem> getItems() {
    return itemList;
  }

  public float getRunningTotal() {
    return runningTotal;
  }

  public int getNumberOfItems() {
    return numberOfItems;
  }

  public int getNumberOfRows() {
    return numberOfRows;
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
}
