package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

public class ItemList {

  private ArrayList<ItemRow> itemRows;
  private float runningTotal;
  private int numberOfItems;
  private int numberOfRows;
  private int indexOfLastChangedRow;

  public ItemList() {
    this.itemRows = new ArrayList<ItemRow>();
    this.runningTotal = 0;
    this.numberOfItems = 0;
    this.numberOfRows = 0;
    this.indexOfLastChangedRow = 0;
  }

  private int indexOfRow(ItemInformationDTO item) {
    ItemInformationDTO itemInRow;
    for(int i = 0; i < itemRows.size(); i++) {
      itemInRow = itemRows.get(i).getItem();
      if(itemInRow == item) {
        return i;
      }
    }
    return -1;
  }

  private void increaseQuantity(int rowIndex, int quantity) {
    itemRows.get(rowIndex).increaseQuantity(quantity);
  }

  private void updateRunningTotalAndNumberOfItems(int quantity) {
    runningTotal += (itemRows.get(indexOfLastChangedRow).getItem().getPrice() * quantity);
    numberOfItems += quantity;
  }

  public void increaseQuantityOfLastScannedItem(int quantity) {
    System.out.println("Increasing quantity of item [" + itemRows.get(indexOfLastChangedRow).getItem().getItemDescription() + "] with " + quantity);
    increaseQuantity(indexOfLastChangedRow, quantity);
    System.out.println("New quantity: " + itemRows.get(indexOfLastChangedRow).getQuantity());
    updateRunningTotalAndNumberOfItems(quantity);
    System.out.println("Running total: " + runningTotal);
  }

  public void addItem(ItemInformationDTO item) {
    int notFound = -1;
    int rowIndex = indexOfRow(item);

    if(rowIndex == notFound) {
      ItemRow newRow = new ItemRow(item);
      itemRows.add(newRow);
      System.out.println("Added item: " + newRow.getItem().getItemDescription());
      numberOfRows++;
      indexOfLastChangedRow = itemRows.size() - 1;
    } else {
      increaseQuantity(rowIndex, 1);
      indexOfLastChangedRow = rowIndex;
    }

    updateRunningTotalAndNumberOfItems(1);
    System.out.println("Running total = " + runningTotal);
  }

  public void addDiscount(SaleDTO saleDTO) {

    ArrayList<ItemRowDTO> newItemRows = saleDTO.getItemRows();

    for(int i = 0; i < itemRows.size(); i++) {
      double oldDiscount = itemRows.get(i).getDiscount();
      double newDiscount = newItemRows.get(i).getDiscount();
      if(oldDiscount != newDiscount) {
        itemRows.get(i).setDiscount(newDiscount);
        runningTotal -= newDiscount;
        System.out.println("Adding discount to ItemList! New Running Total: " + runningTotal);
      }
    }
  }

  public ArrayList<ItemRow> getItemList() {
    return itemRows;
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
