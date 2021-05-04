package se.kth.iv1350.danielhenning.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrinterHandlerTest {

  PrinterHandler printer;

  @BeforeEach
  public void startUp() {
    printer = new PrinterHandler();
  }

  @AfterEach
  public void tearDown() {
    printer = null;
  }

  @Test
  public void testPrinterHandler() {
    boolean expected = true;
    boolean actual = printer!=null;
    assertEquals(expected, actual, "The PrinterHandler is not initiated!");
  }


  
}
