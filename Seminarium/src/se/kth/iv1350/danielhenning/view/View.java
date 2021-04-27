package se.kth.iv1350.danielhenning.view;

import se.kth.iv1350.danielhenning.controller.Controller;

public class View {

  private Controller controller;

  public View(Controller controller) {
    this.controller = controller;
    controller.startSale();
    controller.addItem("1337");
    controller.addQuantity(5);
    controller.endSale();
    controller.requestDiscount("Daniel");
    controller.payment(50);
    System.out.println("Program seems to work!");
  }
}
