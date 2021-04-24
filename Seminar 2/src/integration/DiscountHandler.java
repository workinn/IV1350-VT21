package integration;

import DTO.ClubMemberDTO;
import DTO.ItemInformationDTO;

public class DiscountHandler {

  public DiscountHandler() {

  }

  public ItemInformationDTO addDiscount(ItemInformationDTO item, ClubMemberDTO member) {
    /* 
    Contacts External Discount System to get a SaleDTO with discount added
    */

    ItemInformationDTO newItem;

    if(member.getMemberID() == "1337") {
      newItem = new ItemInformationDTO(item, item.getQuantity(), (item.getPrice() * item.getQuantity() * 0.5));
    } else {
      newItem = item;
    }
    
    return newItem;
  }
}
