package se.kth.iv1350.danielhenning.dto;

import se.kth.iv1350.danielhenning.model.ItemRow;

public class ItemRowDTO {

  ItemInformationDTO item;
  int quantity;
  double discount;
  double price;

  public ItemRowDTO(ItemRow itemRow) {
    this.item = itemRow.getItem();
    this.quantity = itemRow.getQuantity();
    this.discount = itemRow.getDiscount();
    this.price = itemRow.getPrice();
  }
  
}
