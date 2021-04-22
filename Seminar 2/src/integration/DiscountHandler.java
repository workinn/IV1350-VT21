package src.integration;

import src.DTO.ClubMemberDTO;
import src.DTO.ItemInformationDTO;

public class DiscountHandler {

  public DiscountHandler() {

  }

  public ItemInformationDTO addDiscount(ItemInformationDTO item, ClubMemberDTO member) {
    /* 
    Contacts External Discount System to get a SaleDTO with discount added
    */
    
    // Should be SaleDTO from External Discount System
    ItemInformationDTO updatedItem = null;

    return updatedItem;
  }
}
