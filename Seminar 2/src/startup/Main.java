package startup;

import integration.HandlerCreator;
import model.CashRegister;
import controller.Controller;
import view.View;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");

    HandlerCreator integrationHandler = new HandlerCreator();

    int intialBalance = 1000;
    CashRegister cashRegister = new CashRegister(intialBalance);

    Controller controller = new Controller(integrationHandler, cashRegister);
    
    View view = new View(controller);

    controller.startSale();
    controller.addItem("1337");
    controller.addQuantity(5);
    controller.endSale();
    controller.requestDiscount("Daniel");
    controller.payment(50);
    System.out.println("Program seems to work!");
  }
}
