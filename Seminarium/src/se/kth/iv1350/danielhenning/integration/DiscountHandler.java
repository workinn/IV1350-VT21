package se.kth.iv1350.danielhenning.integration;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.model.ItemList;
import se.kth.iv1350.danielhenning.model.ItemRow;

public class DiscountHandler {

  public DiscountHandler() {

  }

  public SaleDTO addDiscount(SaleDTO saleDTO, ClubMemberDTO member) {
    /* 
    Contacts External Discount System to get a SaleDTO with discount added
    */

    ItemList newItems = new ItemList();
    int quantity;
    ItemInformationDTO item;
    boolean lastItemFound = saleDTO.getLastItemFound();

    if(member != null & saleDTO.getRunningTotal() >= 100) {
      System.out.println("Discount found!");
      for(int i = 0; i < saleDTO.getItemRows().size(); i++) {
        quantity = saleDTO.getItemRows().get(i).getQuantity();
        item = saleDTO.getItemRows().get(i).getitem();
        newItems.addItem(item);
        newItems.increaseQuantityOfLastScannedItem(quantity - 1);
        newItems.getItemList().get(i).setDiscount(0.5 * quantity * item.getPrice());
      }
    SaleDTO updateSaleDTO = new SaleDTO(newItems, lastItemFound);
    System.out.println("Discount found! New running total: " + updateSaleDTO.getRunningTotal());

    return updateSaleDTO;
    } else {
      System.out.println("No discounts found!");
      return saleDTO;
    }
  }
}
