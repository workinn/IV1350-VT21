package se.kth.iv1350.danielhenning.startup;

import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.model.CashRegister;
import se.kth.iv1350.danielhenning.controller.Controller;
import se.kth.iv1350.danielhenning.view.View;

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
