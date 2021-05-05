package se.kth.iv1350.danielhenning.view;

import se.kth.iv1350.danielhenning.controller.Controller;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

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
  public View(Controller controller) {
    this.controller = controller;
    String appleID = "1337";
    String colaID = "1";
    String noneExcistingItem ="111111";
    String existingCustomerID = "1337";
    String noneExistingCustomerID = "10";
    SaleDTO currentStateOfSale;

    System.out.println("->Starting new Sale\n");
    controller.startSale();
    System.out.println("->Adding Apple");
    currentStateOfSale = controller.addItem(appleID);
    printSale(currentStateOfSale);
    System.out.println("->Adding Qunatity 10 (+9)");
    currentStateOfSale = controller.addQuantity(10);
    printSale(currentStateOfSale);
    System.out.println("->Adding Cola");
    currentStateOfSale = controller.addItem(colaID);
    printSale(currentStateOfSale);
    System.out.println("->Adding Qunatity 5 (+4)");
    currentStateOfSale = controller.addQuantity(5);
    printSale(currentStateOfSale);
    System.out.println("->Adding none existing item");
    currentStateOfSale = controller.addItem(noneExcistingItem);
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


  public void printSale(SaleDTO currentStateOfSale) {
    if(currentStateOfSale.getLastItemFound() == false) {
      System.out.println("--------------------------");
      System.out.println("|INVALID ITEM IDENTIFIER!|");
      System.out.println("--------------------------");
    }
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
