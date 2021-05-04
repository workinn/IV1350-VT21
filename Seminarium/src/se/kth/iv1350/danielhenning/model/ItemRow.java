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
    if(item != null) {
      this.item = item;
      this.quantity = 1;
      this.discount = 0;
      this.priceIncludingDiscount = item.getPrice();
    }
  }

  /**
   * The method increaseQuantity increases the quantity 
   * of the item in the row by the given quantity
   * @param quantity is the quantity to increase the quantity of the row of
   */
  public void increaseQuantity(int quantity) {
    this.quantity += quantity;
    calculatePrice();
  }

  /**
   * The method setQuantity sets the quantity
   * of the item in the row to the given quantity
   * @param quantity is the quantity to set the rows quantity to
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
    calculatePrice();
  }

  /**
   * The method setDiscount sets the total discount of the row
   * @param discount is the amount of discount to add to the row
   */
  public void setDiscount(double discount) {
    this.discount = discount;
    calculatePrice();
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