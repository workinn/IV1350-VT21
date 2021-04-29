package se.kth.iv1350.danielhenning.dto;

import se.kth.iv1350.danielhenning.model.ItemRow;

/**
 * The class ItemRowDTO represents a Data Transfer Object
 * of one row in the Sale. Each row has an item, it's 
 * quantity, the discount given to the row and a 
 * price for the full row 
 */
public final class ItemRowDTO {

  ItemInformationDTO item;
  int quantity;
  double discount;
  double priceIncludingDiscount;

  /**
   * Creates a new instance of the class ItemRowDTO
   * @param itemRow is the row of an item that is being converted to a DTO
   */
  public ItemRowDTO(ItemRow itemRow) {
    this.item = itemRow.getItem();
    this.quantity = itemRow.getQuantity();
    this.discount = itemRow.getDiscount();
    this.priceIncludingDiscount = itemRow.getPriceIncludingDiscount();
  }

  /**
   * Creates a new instance of the class ItemRowDTO
   * @param itemRow is the row of an item that is being converted to a DTO
   * @param discount is the discount given to the row
   */
  public ItemRowDTO(ItemRowDTO itemRow, double discount) {
    this.item = itemRow.getitem();
    this.quantity = itemRow.getQuantity();
    this.discount = discount;
    this.priceIncludingDiscount = itemRow.getPriceIncludingDiscount() - discount;
  }

  public ItemInformationDTO getitem() {
    return item;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getDiscount() {
    return discount;
  }

  public double getPriceIncludingDiscount() {
    return priceIncludingDiscount;
  }

  public String toString() {
    return item.toString() + " |     " + quantity + "     |    " + discount + "    |   " + priceIncludingDiscount + "    |";
  }
}
