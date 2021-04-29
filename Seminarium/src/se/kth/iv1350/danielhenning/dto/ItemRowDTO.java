package se.kth.iv1350.danielhenning.dto;

import se.kth.iv1350.danielhenning.model.ItemRow;

public final class ItemRowDTO {

  ItemInformationDTO item;
  int quantity;
  double discount;
  double priceIncludingDiscount;

  public ItemRowDTO(ItemRow itemRow) {
    this.item = itemRow.getItem();
    this.quantity = itemRow.getQuantity();
    this.discount = itemRow.getDiscount();
    this.priceIncludingDiscount = itemRow.getPriceIncludingDiscount();
  }

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
