package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

/**
 * RowInItemList
 */
public class ItemRow {

  ItemInformationDTO item;
  int quantity;
  double discount;
  double priceIncludingDiscount;

  public ItemRow(ItemInformationDTO item) {
    this.item = item;
    this.quantity = 1;
    this.discount = 0;
    this.priceIncludingDiscount = item.getPrice();
  }

  public void increaseQuantity(int quantity) {
    this.quantity += quantity;
    calculatePrice();
  }

  public void decreaseQuantity(int quantity) {
    this.quantity -= quantity;
    calculatePrice();
  }

  public void setDiscount(double discount) {
    this.discount = discount;
    calculatePrice();
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
    calculatePrice();
  }

  private void calculatePrice() {
    priceIncludingDiscount = (priceIncludingDiscount - discount) * quantity;
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