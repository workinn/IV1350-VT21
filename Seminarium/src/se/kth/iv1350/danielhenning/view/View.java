package se.kth.iv1350.danielhenning.view;

import se.kth.iv1350.danielhenning.controller.Controller;
import se.kth.iv1350.danielhenning.controller.ItemNotFoundException;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
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
   */
  private SaleDTO currentStateOfSale;

  public View(Controller controller) {
    this.controller = controller;
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
    currentStateOfSale = controller.addQuantity(10);
    printSale(currentStateOfSale);
    addItem(appleID);
    currentStateOfSale = controller.addQuantity(10);
    System.out.println("->Adding none existing item");
    addItem(noneExcistingItem);
    printSale(currentStateOfSale);
    System.out.println("->Loosing Connection");
    addItem(connectionLost);
    printSale(currentStateOfSale);
    System.out.println("->End Sale");
    currentStateOfSale = controller.endSale();
    System.out.println("->Send Discount Request");
    currentStateOfSale = controller.requestDiscount(existingCustomerID);
    System.out.println("->Make Payment 500kr");
    currentStateOfSale = controller.payment(500);
    
    
  }

  private void addItem(String identifier) throws ItemNotFoundException{
    try{
      currentStateOfSale = controller.addItem(identifier);
    }catch(ItemNotFoundException exc){
      System.out.println(exc.getMessage());
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
