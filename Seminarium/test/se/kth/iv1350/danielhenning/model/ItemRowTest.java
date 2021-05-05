package se.kth.iv1350.danielhenning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;

public class ItemRowTest {

  private ItemRow itemRow;

  @BeforeEach
  public void startUp() {
  }

  @AfterEach
  public void tearDown() {
    itemRow = null;
  }

  @Test
  public void testConstructItemRowWithNull() {
    ItemInformationDTO item = null;
    itemRow = new ItemRow(item);

    assertEquals(0, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item, itemRow.getItem(), "The ItemRow's Item is not what was expected: ");
    assertEquals(0, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(0, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }

  @Test
  public void testIncreaseQuantityWithNullItem() {
    ItemInformationDTO item = null;
    itemRow = new ItemRow(item);
    itemRow.increaseQuantity(10);

    assertEquals(0, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item, itemRow.getItem(), "The ItemRow's Item is not what was expected: ");
    assertEquals(0, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(0, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }

  @Test
  public void testSetDiscountWithNullItem() {
    ItemInformationDTO item = null;
    itemRow = new ItemRow(item);
    itemRow.setDiscount(10);

    assertEquals(0, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item, itemRow.getItem(), "The ItemRow's Item is not what was expected: ");
    assertEquals(0, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(0, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }

  @Test
  public void testConstructorWithApple() {
    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemRow = new ItemRow(item);

    assertEquals(0, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item.getItemDescription(), itemRow.getItem().getItemDescription(), "The ItemRow's ItemDescription is not what was expected: ");
    assertEquals(item.getItemIdentifier(), itemRow.getItem().getItemIdentifier(), "The ItemRow's ItemID is not what was expected: ");
    assertEquals(item.getPrice(), itemRow.getItem().getPrice(), "The ItemRow's ItemPrice is not what was expected: ");
    assertEquals(item.getVATrate(), itemRow.getItem().getVATrate(), "The ItemRow's ItemVATrate is not what was expected: ");
    assertEquals(10, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(1, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }

  @Test
  public void testIncreaseQuantityWithApple() {
    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemRow = new ItemRow(item);
    itemRow.increaseQuantity(9);

    assertEquals(0, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item.getItemDescription(), itemRow.getItem().getItemDescription(), "The ItemRow's ItemDescription is not what was expected: ");
    assertEquals(item.getItemIdentifier(), itemRow.getItem().getItemIdentifier(), "The ItemRow's ItemID is not what was expected: ");
    assertEquals(item.getPrice(), itemRow.getItem().getPrice(), "The ItemRow's ItemPrice is not what was expected: ");
    assertEquals(item.getVATrate(), itemRow.getItem().getVATrate(), "The ItemRow's ItemVATrate is not what was expected: ");
    assertEquals(10 * 10, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(10, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }

  @Test
  public void testIncreaseQuantityNegativeNumberAndWithApple() {
    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemRow = new ItemRow(item);
    itemRow.increaseQuantity(-11);

    assertEquals(0, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item.getItemDescription(), itemRow.getItem().getItemDescription(), "The ItemRow's ItemDescription is not what was expected: ");
    assertEquals(item.getItemIdentifier(), itemRow.getItem().getItemIdentifier(), "The ItemRow's ItemID is not what was expected: ");
    assertEquals(item.getPrice(), itemRow.getItem().getPrice(), "The ItemRow's ItemPrice is not what was expected: ");
    assertEquals(item.getVATrate(), itemRow.getItem().getVATrate(), "The ItemRow's ItemVATrate is not what was expected: ");
    assertEquals(1 * 10, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(1, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }

  @Test
  public void testSetDiscountWithApple() {
    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemRow = new ItemRow(item);
    itemRow.setDiscount(10);

    assertEquals(10, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item.getItemDescription(), itemRow.getItem().getItemDescription(), "The ItemRow's ItemDescription is not what was expected: ");
    assertEquals(item.getItemIdentifier(), itemRow.getItem().getItemIdentifier(), "The ItemRow's ItemID is not what was expected: ");
    assertEquals(item.getPrice(), itemRow.getItem().getPrice(), "The ItemRow's ItemPrice is not what was expected: ");
    assertEquals(item.getVATrate(), itemRow.getItem().getVATrate(), "The ItemRow's ItemVATrate is not what was expected: ");
    assertEquals(1 * 10 - 10, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(1, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }

  @Test
  public void testSetDiscountToMoreThanPriceWithApple() {
    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemRow = new ItemRow(item);
    itemRow.setDiscount(100);

    assertEquals(10, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item.getItemDescription(), itemRow.getItem().getItemDescription(), "The ItemRow's ItemDescription is not what was expected: ");
    assertEquals(item.getItemIdentifier(), itemRow.getItem().getItemIdentifier(), "The ItemRow's ItemID is not what was expected: ");
    assertEquals(item.getPrice(), itemRow.getItem().getPrice(), "The ItemRow's ItemPrice is not what was expected: ");
    assertEquals(item.getVATrate(), itemRow.getItem().getVATrate(), "The ItemRow's ItemVATrate is not what was expected: ");
    assertEquals(1 * 10 - 10, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(1, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }

  @Test
  public void testSetDiscountToNegativeNumberWithApple() {
    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemRow = new ItemRow(item);
    itemRow.setDiscount(-10);

    assertEquals(0, itemRow.getDiscount(), "The ItemRow's discount is not what was expected: ");
    assertEquals(item.getItemDescription(), itemRow.getItem().getItemDescription(), "The ItemRow's ItemDescription is not what was expected: ");
    assertEquals(item.getItemIdentifier(), itemRow.getItem().getItemIdentifier(), "The ItemRow's ItemID is not what was expected: ");
    assertEquals(item.getPrice(), itemRow.getItem().getPrice(), "The ItemRow's ItemPrice is not what was expected: ");
    assertEquals(item.getVATrate(), itemRow.getItem().getVATrate(), "The ItemRow's ItemVATrate is not what was expected: ");
    assertEquals(1 * 10, itemRow.getPriceIncludingDiscount(), "The ItemRow's Price is not what was expected: ");
    assertEquals(1, itemRow.getQuantity(), "The ItemRow's Quantity is not what was expected: ");
  }
}
