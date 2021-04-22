package src.integration;

import src.DTO.ItemInformationDTO;
import src.DTO.SaleDTO;


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

    // returnedItem contains information from External Inventory System, item not found returns null.
    ItemInformationDTO returnedItem = null;
    return returnedItem;
  }
}
