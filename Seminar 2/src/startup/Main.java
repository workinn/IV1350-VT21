package src.startup;

import src.integration.HandlerCreator;
import src.model.CashRegister;
import src.controller.Controller;
import src.view.View;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");

    HandlerCreator integrationHandler = new HandlerCreator();

    int intialBalance = 1000;
    CashRegister cashRegister = new CashRegister(intialBalance);

    Controller controller = new Controller(integrationHandler, cashRegister);
    
    View view = new View(controller);
  }
}
