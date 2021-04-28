package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

public class ItemList {

  private ArrayList<ItemRow> itemRows;
  private double runningTotal;
  private double totalDiscount;
  private int numberOfItems;
  private int numberOfRows;
  private int indexOfLastChangedRow;

  public ItemList() {
    this.itemRows = new ArrayList<ItemRow>();
    this.runningTotal = 0;
    this.totalDiscount = 0;
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
//Onödig om running total görs i sale, kanske endast update total items.
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
      //System.out.println("Added item: " + newRow.getItem().getItemDescription());
      numberOfRows++;
      indexOfLastChangedRow = itemRows.size() - 1;
    } else {
      increaseQuantity(rowIndex, 1);
      indexOfLastChangedRow = rowIndex;
    }

    updateRunningTotalAndNumberOfItems(1);
  }
// frågan om denna ska göras från här, kanske göras från Sale iställeT?
  public void addDiscount(DiscountDTO discount) {

    System.out.println("Running total before discounts: " + runningTotal);

    for(int i = 0; i < itemRows.size(); i++) {
      double oldDiscount = itemRows.get(i).getDiscount();
      double newDiscount = discount.getItemRowDTO().get(i).getDiscount();
      if(oldDiscount != newDiscount) {
        itemRows.get(i).setDiscount(newDiscount);
        runningTotal -= newDiscount;
        System.out.println("Adding discount to ItemList! New Running Total: " + runningTotal);
      }
    }
    totalDiscount = discount.getTotalSaleDiscount();
    System.out.println("Adding total discount to Sale: " + totalDiscount);
    runningTotal -= totalDiscount;
    System.out.println("Final running total = " + runningTotal);
  }

  public ArrayList<ItemRow> getItemList() {
    return itemRows;
  }

  public double getRunningTotal() {
    return runningTotal;
  }

  public int getNumberOfItems() {
    return numberOfItems;
  }

  public int getNumberOfRows() {
    return numberOfRows;
  }
}
