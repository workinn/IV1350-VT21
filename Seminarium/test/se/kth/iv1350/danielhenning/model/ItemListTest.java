package se.kth.iv1350.danielhenning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;

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
  public void testConstructor() {
    int expectedNumberOfItems = 0;
    int expectedNumberOfRows = 0;
    int actualNumberOfItems = itemList.getNumberOfItems();
    int actualNumberOfRows = itemList.getNumberOfRows();
    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows s not what was expected: ");
    assertEquals(0, itemList.getItemRows().size(), "The size of ArrayList<ItemRow> is not what was expected: ");
  }

  @Test
  public void testAddItemWithNull() {

    ItemInformationDTO item = null;
    itemList.addItem(item);

    int expectedNumberOfItems = 0;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 0;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
  }

  @Test
  public void testAddItemWith1Apple() {

    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemList.addItem(item);

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(item));
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 1;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWith2Apple() {

    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemList.addItem(item);
    itemList.addItem(item);

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(item));
    expectedItemRows.get(0).increaseQuantity(1);
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 2;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWith3Apple() {

    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemList.addItem(item);
    itemList.addItem(item);
    itemList.addItem(item);

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(item));
    expectedItemRows.get(0).increaseQuantity(2);
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 3;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWith1Apple1null() {

    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemList.addItem(item);
    itemList.addItem(null);

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(item));
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 1;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWith1Apple1Cola() {

    ItemInformationDTO apple = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    ItemInformationDTO cola = new ItemInformationDTO(25, 0.10, "Coca-Cola", "1");
    itemList.addItem(apple);
    itemList.addItem(cola);

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(apple));
    expectedItemRows.add(new ItemRow(cola));
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 2;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 2;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWith10Apple10Cola() {

    ItemInformationDTO apple = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    ItemInformationDTO cola = new ItemInformationDTO(25, 0.10, "Coca-Cola", "1");
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(apple);
    itemList.addItem(cola);
    itemList.addItem(cola);
    itemList.addItem(cola);
    itemList.addItem(cola);
    itemList.addItem(cola);
    itemList.addItem(cola);
    itemList.addItem(cola);
    itemList.addItem(cola);
    itemList.addItem(cola);
    itemList.addItem(cola);

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(apple));
    expectedItemRows.add(new ItemRow(cola));
    expectedItemRows.get(0).increaseQuantity(9);
    expectedItemRows.get(1).increaseQuantity(9);
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 20;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 2;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testIncreaseQuantityOfLastScannedItemWithNoItem() {

    itemList.increaseQuantityOfLastScannedItem(1);

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 0;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 0;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testIncreaseQuantityOfLastScannedItemWithAppleAndNegativeQuantity() {

    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemList.addItem(item);
    itemList.increaseQuantityOfLastScannedItem(-10);
    

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(item));
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 1;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testIncreaseQuantityOfLastScannedItemWithAppleAndPositiveQuantity() {

    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemList.addItem(item);
    itemList.increaseQuantityOfLastScannedItem(9);
    

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(item));
    expectedItemRows.get(0).increaseQuantity(9);
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 10;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testIncreaseQuantityOfLastScannedItemWithAppleAndZeroInQuantity() {

    ItemInformationDTO item = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    itemList.addItem(item);
    itemList.increaseQuantityOfLastScannedItem(0);
    

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(item));
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 1;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemAppleAndColaAndIncreaseQuantityOfLastScannedItemWith10() {

    ItemInformationDTO apple = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    ItemInformationDTO cola = new ItemInformationDTO(25, 0.10, "Coca-Cola", "1");
    itemList.addItem(apple);
    itemList.addItem(cola);
    itemList.increaseQuantityOfLastScannedItem(9);
    

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(apple));
    expectedItemRows.add(new ItemRow(cola));
    expectedItemRows.get(1).increaseQuantity(9);
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 11;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 2;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddDiscountWithoutItemsAndNullDiscountDTO() {

    DiscountDTO discount = null;
    itemList.addDiscount(discount);    

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 0;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 0;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddDiscountWithAppleAndNullDiscountDTO() {

    ItemInformationDTO apple = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    DiscountDTO discount = null;
    itemList.addItem(apple);
    itemList.addDiscount(discount);    

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(apple));
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 1;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddDiscountWithoutItemsAndExistingDiscountDTO() {

    ItemInformationDTO apple = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    ArrayList<ItemRowDTO> itemRows = new ArrayList<ItemRowDTO>();
    ItemRow itemRow = new ItemRow(apple);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    itemRowDTO = new ItemRowDTO(itemRowDTO, 10);
    itemRows.add(itemRowDTO);
    ClubMemberDTO member = new ClubMemberDTO("Daniel", "1337");
    DiscountDTO discount = new DiscountDTO(itemRows, member, 10);
    itemList.addDiscount(discount);    

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 0;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 0;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddDiscountWith1AppleAndExistingDiscountDTO() {

    ItemInformationDTO apple = new ItemInformationDTO(10.0, 0.10, "Äpple", "1337");
    ArrayList<ItemRowDTO> itemRows = new ArrayList<ItemRowDTO>();
    ItemRow itemRow = new ItemRow(apple);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    itemRowDTO = new ItemRowDTO(itemRowDTO, 10);
    itemRows.add(itemRowDTO);
    ClubMemberDTO member = new ClubMemberDTO("Daniel", "1337");
    DiscountDTO discount = new DiscountDTO(itemRows, member, 10);
    itemList.addItem(apple);
    itemList.addDiscount(discount);    

    ArrayList<ItemRow> expectedItemRows = new ArrayList<ItemRow>();
    expectedItemRows.add(new ItemRow(apple));
    expectedItemRows.get(0).setDiscount(10);
    ArrayList<ItemRow> actualItemRows = itemList.getItemRows();

    int expectedNumberOfItems = 1;
    int actualNumberOfItems = itemList.getNumberOfItems();

    int expectedNumberOfRows = 1;
    int actualNumberOfRows = itemList.getNumberOfRows();

    assertEquals(expectedNumberOfItems, actualNumberOfItems, "The number of items in ItemList is not what was expected: ");
    assertEquals(expectedNumberOfRows, actualNumberOfRows, "The number of rows in ItemList is not what was expected: ");
    
    for(int i = 0; i < actualItemRows.size(); i++) {
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actualItemRows.get(i).getItem().getItemIdentifier(), "ItemID of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actualItemRows.get(i).getItem().getItemDescription(), "ItemDescription of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actualItemRows.get(i).getItem().getPrice(), "ItemPrice of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actualItemRows.get(i).getItem().getVATrate(), "ItemVATrate of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getDiscount(), actualItemRows.get(i).getDiscount(), "Discount of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actualItemRows.get(i).getPriceIncludingDiscount(), "Price of row" + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actualItemRows.get(i).getQuantity(), "Quantity of row" + i + " is not what was expected: ");
    }
  }
}
