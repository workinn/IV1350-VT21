package se.kth.iv1350.danielhenning.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.model.CashRegister;

public class ControllerTest {

  private Controller controller;

  @BeforeEach
  public void startUp() {
    HandlerCreator handler = new HandlerCreator();
    CashRegister cashRegister = new CashRegister(1000);
    controller = new Controller(handler, cashRegister);
  }

  @AfterEach
  public void tearDown() {
    controller = null;
  }

  public void testStartSale() {
  }
  
}
