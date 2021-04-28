package se.kth.iv1350.danielhenning.integration;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

public class InventoryHandler {

  public InventoryHandler() {

  }
  
  public void updateInventory(SaleDTO saleDTO){
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
      item = new ItemInformationDTO(10.0, 0.10, "Äpple", itemIdentifier);
    } else if (itemIdentifier == "1") {
      item = new ItemInformationDTO(25, 0.10, "Coca-Cola", itemIdentifier);
    } 
    else {
      item = null;
    }

    return item;
  }
}
