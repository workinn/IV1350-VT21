package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

/**
 * The ItemRow class represents a row in the sale. Each
 * row has an item, the quantity of the item, discount
 * given for the whole row and price for the whole row
 */
public class ItemRow {

  private ItemInformationDTO item;
  private int quantity;
  private double discount;
  private double priceIncludingDiscount;

  /**
   * Creates a new instance of the class ItemRow
   * @param item is the item in the row
   */
  public ItemRow(ItemInformationDTO item) {
    this.item = item;
    this.discount = 0;
    if(item != null) {
      this.quantity = 1;
      this.priceIncludingDiscount = item.getPrice();
    } else {
      this.quantity = 0;
      this.priceIncludingDiscount = 0;
    }
  }

  /**
   * The method increaseQuantity increases the quantity 
   * of the item in the row by the given quantity
   * @param quantity is the quantity to increase the quantity of the row of
   */
  public void increaseQuantity(int quantity) {
    if(item != null) {
      if(quantity > 0) {
        this.quantity += quantity;
        calculatePrice();
      }
    }
  }

  /**
   * The method setDiscount sets the total discount of the row
   * @param discount is the amount of discount to add to the row
   */
  public void setDiscount(double discount) {
    if(item != null) {
      if(discount >= 0) {
        if(discount > priceIncludingDiscount) {
          this.discount = priceIncludingDiscount;
        } else {
          this.discount = discount;
        }
        calculatePrice();
      }
    }
  }

  private void calculatePrice() {
    priceIncludingDiscount = (item.getPrice() * quantity) - discount;
  }

  public ItemInformationDTO getItem() {
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
}