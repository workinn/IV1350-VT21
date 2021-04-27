package se.kth.iv1350.danielhenning.integration;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleLogDTO;


public class InventoryHandler {

  public InventoryHandler() {

  }
  
  public void updateInventory(SaleLogDTO saleLog){
    /*
    Updates External Inventory System
    */
  }

  public ItemInformationDTO getItemInformation(String itemIdentifier){
    /*
    Gets item information from External Inventory System 
    */

    ItemInformationDTO item;

    if(itemIdentifier == "1337") {
      item = new ItemInformationDTO(10.0, 0.10, "Äpple", itemIdentifier, 0.0);
    } else {
      item = null;
    }

    return item;
  }
}
