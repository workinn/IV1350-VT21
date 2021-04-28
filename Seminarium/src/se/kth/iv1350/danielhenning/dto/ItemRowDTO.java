package se.kth.iv1350.danielhenning.dto;

import se.kth.iv1350.danielhenning.model.ItemRow;

public class ItemRowDTO {

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
}
