package se.kth.iv1350.danielhenning.view;

import se.kth.iv1350.danielhenning.controller.Controller;
import se.kth.iv1350.danielhenning.controller.ItemNotFoundException;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.CouldNotConnectToServerException;
import se.kth.iv1350.danielhenning.integration.ItemDoesNotExistException;
import se.kth.iv1350.danielhenning.util.TotalRevenueFileOutput;

/**
 * The View class represents the the view of the program
 */
public class View {

  private Controller controller;

  /**
   * Constructor for the View. The constructor contains
   * hardcoded commands for the program.
   * 
   * @param controller is the controller of the program
   * @throws ItemNotFoundException this exception is catched by the view
   * @throws CouldNotConnectToServerException this exception is used in Controller and not view
   * @throws ItemDoesNotExistException this exception is used in Controller and not view
   */
  private SaleDTO currentStateOfSale;

  public View(Controller controller) throws ItemNotFoundException, CouldNotConnectToServerException, ItemDoesNotExistException {
    this.controller = controller;    
    testProgram();
  }

  private void testProgram() throws CouldNotConnectToServerException, ItemNotFoundException, ItemDoesNotExistException {

    String appleID = "1337";
    String colaID = "1";
    String noneExcistingItem ="1111";
    String existingCustomerID = "1337";
    String noneExistingCustomerID = "10";
    String connectionLost = "5555";
    controller.addTotalRevenueObeserver(new TotalRevenueView());
    controller.addTotalRevenueObeserver(new TotalRevenueFileOutput());

    System.out.println("->Starting new Sale\n");
    controller.startSale();
    System.out.println("->Adding Cola");
    addItem(colaID);
    printSale(currentStateOfSale);
    System.out.println("->Add quantity 10");
    currentStateOfSale = controller.addQuantity(10);
    printSale(currentStateOfSale);
    System.out.println("->Adding Apple");
    addItem(appleID);
    printSale(currentStateOfSale);
    System.out.println("->Add quantity 5");
    currentStateOfSale = controller.addQuantity(5);
    printSale(currentStateOfSale);
    System.out.println("->Adding none existing item");
    addItem(noneExcistingItem);
    printSale(currentStateOfSale);
    System.out.println("->Loosing Connection");
    addItem(connectionLost);
    printSale(currentStateOfSale);
    System.out.println("->End Sale");
    currentStateOfSale = controller.endSale();
    printSale(currentStateOfSale);
    System.out.println("->Send Discount Request");
    currentStateOfSale = controller.requestDiscount(existingCustomerID);
    printSale(currentStateOfSale);
    System.out.println("->Make Payment 500kr");
    currentStateOfSale = controller.payment(500);

  }

  private void addItem(String identifier) throws ItemNotFoundException, CouldNotConnectToServerException, ItemDoesNotExistException{
    try{
      currentStateOfSale = controller.addItem(identifier);
    }catch(ItemNotFoundException exc){
      System.out.println("Item "+identifier+" not found, try again");
    }

  }


  private void printSale(SaleDTO currentStateOfSale) {
    System.out.println("---------------------------------------------------------------");
    System.out.println("| Row | Description | ID  | Quantity  | Discount  | Row Price |");
    System.out.println("---------------------------------------------------------------");
    for(int i = 0; i < currentStateOfSale.getItemRows().size(); i++) {
      System.out.println("|  " + (i + 1) + "  |   " + currentStateOfSale.getItemRows().get(i).toString());
      System.out.println("---------------------------------------------------------------");
    }
    System.out.println("Discount on whole sale: " + currentStateOfSale.getDiscountOnWholeSale());
    System.out.println("Running Total: " + currentStateOfSale.getRunningTotal() + "             Number Of Items: " + currentStateOfSale.getNumberOfItems());
    System.out.println("Amount Paid: " + currentStateOfSale.getAmountPaid() + "               Change: " + currentStateOfSale.getChange());
    System.out.print("Date: " + currentStateOfSale.getDateTime().getYear() + "-" + currentStateOfSale.getDateTime().getMonth().getValue() + "-" + currentStateOfSale.getDateTime().getDayOfMonth());

    int minute = currentStateOfSale.getDateTime().getMinute();
    String min;

    if(minute < 10) {
      min = "0" + minute;
    } else{
      min = "" + minute;
    }

    System.out.println("                  Time: " + currentStateOfSale.getDateTime().getHour() + ":" + min + "\n");
  }

}
