package se.kth.iv1350.danielhenning.integration;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DiscountHandlerTest {

  private DiscountHandler discountHandler;

  @BeforeEach
  public void startUp() {
    discountHandler = new DiscountHandler();
  }

  @AfterEach
  public void tearDown() {
    discountHandler = null;
  }

  @Test
  public void testGetDiscountForAppleAndNotMember () {

  }
  
}
