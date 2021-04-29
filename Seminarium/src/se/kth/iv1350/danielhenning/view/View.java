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

    SaleDTO currentStateOfSale;

    controller.startSale();
    currentStateOfSale = controller.addItem("1337");
    currentStateOfSale.printSale();
    currentStateOfSale = controller.addQuantity(10);
    currentStateOfSale.printSale();
    currentStateOfSale = controller.addItem("1");
    currentStateOfSale.printSale();
    currentStateOfSale = controller.addQuantity(15);
    currentStateOfSale.printSale();
    currentStateOfSale = controller.endSale();
    currentStateOfSale.printSale();
    currentStateOfSale = controller.requestDiscount("Daniel");
    currentStateOfSale.printSale();
    currentStateOfSale = controller.payment(500);
    currentStateOfSale.printSale();
    System.out.println("Program seems to work!");
  }
}
