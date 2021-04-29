package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

import java.util.ArrayList;

public class ItemList {

  private ArrayList<ItemRow> itemRows;
  private int numberOfItems;
  private int numberOfRows;
  private int indexOfLastChangedRow;

  public ItemList() {
    this.itemRows = new ArrayList<ItemRow>();
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
    numberOfItems += quantity;
  }

  public void increaseQuantityOfLastScannedItem(int quantity) {
    increaseQuantity(indexOfLastChangedRow, quantity);
  }

  public void addItem(ItemInformationDTO item) {
    int notFound = -1;
    int rowIndex = indexOfRow(item);

    if(rowIndex == notFound) {
      ItemRow newRow = new ItemRow(item);
      itemRows.add(newRow);
      numberOfRows++;
      numberOfItems++;
      indexOfLastChangedRow = itemRows.size() - 1;
    } else {
      increaseQuantity(rowIndex, 1);
      indexOfLastChangedRow = rowIndex;
      numberOfItems++;
    }
  }

  public void addDiscount(DiscountDTO discount) {

    for(int i = 0; i < itemRows.size(); i++) {
      double oldDiscount = itemRows.get(i).getDiscount();
      double newDiscount = discount.getItemRowDTO().get(i).getDiscount();
      if(oldDiscount != newDiscount) {
        itemRows.get(i).setDiscount(newDiscount);
      }
    }
  }

  public ArrayList<ItemRow> getItemRows() {
    return itemRows;
  }

  public int getNumberOfItems() {
    return numberOfItems;
  }

  public int getNumberOfRows() {
    return numberOfRows;
  }
}
