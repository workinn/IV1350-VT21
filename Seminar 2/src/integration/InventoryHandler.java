package integration;

import DTO.ItemInformationDTO;
import DTO.SaleDTO;


public class InventoryHandler {

  public InventoryHandler() {

  }
  
  public void updateInventory(SaleDTO sale){
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
