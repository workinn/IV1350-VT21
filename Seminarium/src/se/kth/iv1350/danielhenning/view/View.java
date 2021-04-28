package se.kth.iv1350.danielhenning.view;

import se.kth.iv1350.danielhenning.controller.Controller;

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

    controller.startSale();
    controller.addItem("1337");
    controller.addQuantity(10);
    controller.endSale();
    controller.requestDiscount("Daniel");
    controller.payment(500);
    System.out.println("Program seems to work!");
  }
}
