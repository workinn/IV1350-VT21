package se.kth.iv1350.danielhenning.integration;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

/**
 * The InventoryHandler class represents the interface
 * between the System Under Development and an
 * External Inventory System
 */
public class InventoryHandler {

  /**
   * Creates a new instance of the class InventoryHandler
   */
  public InventoryHandler() {
  }
  
  /**
   * The method updateInventory updates an External Inventory System
   * @param saleDTO contains all information about the sale to update an External Accounting System
   */
  public void updateInventory(SaleDTO saleDTO){
  }

  /**
   * The method getItemInformation retrieves all information for
   * an item from an External Inventory System. Right now, this 
   * method contains hard coded items to test the program
   * @param itemIdentifier is the identifier (bar code) of the searched item
   * @return a ItemInformationDTO containing all the items information. May return null if item is not found
   */
  public ItemInformationDTO getItemInformation(String itemIdentifier){

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
