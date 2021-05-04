package se.kth.iv1350.danielhenning.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

public class InventoryHandlerTest {

  InventoryHandler inventoryHandler;

  @BeforeEach
  public void startUp() {
    inventoryHandler = new InventoryHandler();
  }

  @AfterEach
  public void tearDown() {
    inventoryHandler = null;
  }

  @Test
  public void testGetItemInformationApple() {
    String itemIdentifier = "1337";
    ItemInformationDTO expected = new ItemInformationDTO(10.0, 0.10, "Äpple", itemIdentifier);
    ItemInformationDTO actual = inventoryHandler.getItemInformation(itemIdentifier);

    assertEquals(expected.getClass(), actual.getClass(), "The given object from getItemInformation is not of the expect class: expected: " + expected.getClass() + " actual: " + actual.getClass());
    assertEquals(expected.getItemDescription(), actual.getItemDescription(), "The given ItemDescription is not what was expected: expected: " + expected.getItemDescription() + " actual: " + actual.getItemDescription());
    assertEquals(expected.getItemIdentifier(), actual.getItemIdentifier(), "The given ItemIdentifier is not what was expected: expected: " + expected.getItemIdentifier() + " actual: " + actual.getItemIdentifier());
    assertEquals(expected.getPrice(), actual.getPrice(), "The given Price is not what was expected: expected: " + expected.getPrice() + " actual: " + actual.getPrice());
    assertEquals(expected.getVATrate(), actual.getVATrate(), "The given VATrate is not what was expected: expected: " + expected.getVATrate() + " actual: " + actual.getVATrate());
  }

  @Test
  public void testGetItemInformationCola() {
    String itemIdentifier = "1";
    ItemInformationDTO expected = new ItemInformationDTO(25, 0.10, "Coca-Cola", itemIdentifier);
    ItemInformationDTO actual = inventoryHandler.getItemInformation(itemIdentifier);

    assertEquals(expected.getClass(), actual.getClass(), "The given object from getItemInformation is not of the expect class: expected: " + expected.getClass() + " actual: " + actual.getClass());
    assertEquals(expected.getItemDescription(), actual.getItemDescription(), "The given ItemDescription is not what was expected: expected: " + expected.getItemDescription() + " actual: " + actual.getItemDescription());
    assertEquals(expected.getItemIdentifier(), actual.getItemIdentifier(), "The given ItemIdentifier is not what was expected: expected: " + expected.getItemIdentifier() + " actual: " + actual.getItemIdentifier());
    assertEquals(expected.getPrice(), actual.getPrice(), "The given Price is not what was expected: expected: " + expected.getPrice() + " actual: " + actual.getPrice());
    assertEquals(expected.getVATrate(), actual.getVATrate(), "The given VATrate is not what was expected: expected: " + expected.getVATrate() + " actual: " + actual.getVATrate());
  }

  @Test
  public void testGetItemInformationWithNotExistingItem() {
    String itemIdentifier = "0";
    ItemInformationDTO expected = null;
    ItemInformationDTO actual = inventoryHandler.getItemInformation(itemIdentifier);

    assertEquals(expected, actual, "The given ItemInformationDTO is not what was expected: expected: " + expected + " actual: "+ actual);
  }
  
}
