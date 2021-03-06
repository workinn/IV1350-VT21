package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

import java.util.ArrayList;

/**
 * The ItemList class represents the whole list of items
 * that makes up the sale. The class contains a list of
 * ItemRows, the total number of items in the list and
 * the number of rows in the list. The variable 
 * indexOfLastChangedRow is used for inner logic
 */
public class ItemList {

  private ArrayList<ItemRow> itemRows;
  private int numberOfItems;
  private int numberOfRows;
  private int indexOfLastChangedRow;

  /**
   * Creates a new instance of the class ItemList
   */
  public ItemList() {
    this.itemRows = new ArrayList<ItemRow>();
    this.numberOfItems = 0;
    this.numberOfRows = 0;
    this.indexOfLastChangedRow = -1;
  }

  private int indexOfRow(ItemInformationDTO item) {
    String itemIDInRow;
    if(item == null) {
      return -1;
    }
    for(int i = 0; i < itemRows.size(); i++) {
      itemIDInRow = itemRows.get(i).getItem().getItemIdentifier();
      if(itemIDInRow == item.getItemIdentifier()) {
        return i;
      }
    }
    return -1;
  }

  private void increaseQuantity(int rowIndex, int quantity) {
    itemRows.get(rowIndex).increaseQuantity(quantity);
    numberOfItems += quantity;
  }

  /**
   * The method addItem adds the given item to the list of ItemRows.
   * If a row with the same item already exists, it increases the 
   * quantity by 1. If a row with the same item does not exist,
   * a new row with the given item is created and added to the list
   * of ItemRows
   * @param item is the item to add to the list of ItemRows
   */
  public void addItem(ItemInformationDTO item) {
    int notFound = -1;
    int rowIndex = indexOfRow(item);

    if(item != null) {
      if(rowIndex == notFound) {
        ItemRow newRow = new ItemRow(item);
        itemRows.add(newRow);
        numberOfRows++;
        numberOfItems++;
        indexOfLastChangedRow = itemRows.size() - 1;
      } else {
        increaseQuantity(rowIndex, 1);
        indexOfLastChangedRow = rowIndex;
      }
    }
  }

  /**
   * The method increaseQuantityOfLastScannedItem increases the quantity
   * of the last scanned item by the given quantity
   * @param quantity is the quantity to increase the quantity of an item
   */
  public void increaseQuantityOfLastScannedItem(int quantity) {
    if(indexOfLastChangedRow >= 0) {
      if(quantity > 0) {
        increaseQuantity(indexOfLastChangedRow, quantity);
      }
    }
  }

  /**
   * The method addDiscount adds discount to the rows in the list
   * of ItemRows. This is done by comparing and adding discounts
   * from a given DiscountDTO
   * @param discount has the discounts to be included to the list of ItemRows
   */
  public void addDiscount(DiscountDTO discount) {
    if(discount != null) {
      for(int i = 0; i < itemRows.size(); i++) {
        double oldDiscount = itemRows.get(i).getDiscount();
        double newDiscount = discount.getItemRowDTO().get(i).getDiscount();
        if(oldDiscount != newDiscount) {
          itemRows.get(i).setDiscount(newDiscount);
        }
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
