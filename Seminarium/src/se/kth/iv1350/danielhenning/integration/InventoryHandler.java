package se.kth.iv1350.danielhenning.integration;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

/**
 * The InventoryHandler class represents the interface
 * between the System Under Development and an
 * External Inventory System
 */
public class InventoryHandler {
  private static final InventoryHandler INSTANCE = new InventoryHandler();
  /**
   * Creates a new instance of the class InventoryHandler
   */
  private InventoryHandler() {
  }
  /**
   * Returns this instance of inventory handler
   * @return this inventory handler.
   */
  public static InventoryHandler getInventoryHandler(){
    return INSTANCE;
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
   * @throws ItemDoesNotExistException throws this exception when no item is found.
   * @throws CouldNotConnectToServerException throws this exception when connection to server is lost.
   * Sense we dont have a database this is simulated by checking for the item identifier 5555
   *    */
  public ItemInformationDTO getItemInformation(String itemIdentifier) throws ItemDoesNotExistException{

    ItemInformationDTO item = null;
    if(itemIdentifier == "5555"){
      throw new CouldNotConnectToServerException("Lost connection to inventory databaseserver");
    }
    if(itemIdentifier == "1337") {
      item = new ItemInformationDTO(10.0, 0.10, "Äpple", itemIdentifier);
    } if (itemIdentifier == "1") {
      item = new ItemInformationDTO(25, 0.10, "Coca-Cola", itemIdentifier);
    } if(item==null){
      throw new ItemDoesNotExistException("No such item: " + itemIdentifier);
    }
    return item;
  }
}
