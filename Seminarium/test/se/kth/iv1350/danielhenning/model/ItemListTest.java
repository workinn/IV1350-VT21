package se.kth.iv1350.danielhenning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemListTest {

  ItemList itemList;

  @BeforeEach
  public void startUp() {
    itemList = new ItemList();
  }

  @AfterEach
  public void tearDown() {
    itemList = null;
  }

  @Test
  public void testAddItem() {

  }
  
}
