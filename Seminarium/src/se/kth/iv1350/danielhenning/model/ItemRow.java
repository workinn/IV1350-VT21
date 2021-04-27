package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

/**
 * RowInItemList
 */
public class RowOfItem {

  ItemInformationDTO item;
  int quantity;
  double discount;
  double price;

  public RowOfItem(ItemInformationDTO item) {
    this.item = item;
    this.quantity = 1;
    this.discount = 0;
    this.price = item.getPrice();
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

  private void calculatePrice() {
    price = (price - discount) * quantity;
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

  public double getPrice() {
    return price;
  }
}