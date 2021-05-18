package se.kth.iv1350.danielhenning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;

public class SaleTest {

  private Sale sale;
  //private Discount discount;
  private SaleLog saleLog;
  private HandlerCreator handler;


  @BeforeEach
  public void startUp() {
    handler = new HandlerCreator();
    saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    //discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    sale = new Sale(handler, saleLog/*, discount*/);


  }

  @AfterEach
  public void tearDown() {
    handler = null;
    saleLog = null;
    //discount = null;
    sale = null;
  }
  
  @Test
  public void testAddItemWithNullString() {
    SaleDTO actual = new SaleDTO(sale);
    try {
      actual = sale.addItem(null);
    } catch (Exception e) {
      //TODO: handle exception
    }
    

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();

    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(0, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }
  

  @Test
  public void testAddItemWithNoneExistingItem() {
    SaleDTO actual = new SaleDTO(sale);
    try {
      actual = sale.addItem("100");
    } catch (Exception e) {
      //TODO: handle exception
    }
    

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();

    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(0, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWith1Apple() {
    String itemIdentifier = "1337";
    SaleDTO actual = sale.addItem(itemIdentifier);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(new ItemRow(apple)));

    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(1, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(10, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWithSameItemTwoTimesInARow() {
    String itemIdentifier = "1337";
    SaleDTO actual = sale.addItem(itemIdentifier);
    actual = sale.addItem(itemIdentifier);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(1);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));

    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(2, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(20, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWithTwoDifferentItems() {
    String appleID = "1337";
    String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addItem(colaID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    //itemRow.increaseQuantity(2);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    itemRow = new ItemRow(cola);
    expectedItemRows.add(new ItemRowDTO(itemRow));

    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(2, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(35, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWith1ExistingItemAnd1NoneExistingItem() {
    String appleID = "1337";
    String notExistingID = "100";
    SaleDTO actual = sale.addItem(appleID);
    try {
      actual = sale.addItem(notExistingID);
    } catch (Exception e) {
      
    }

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    ItemRow itemRow = new ItemRow(apple);
    //itemRow.increaseQuantity(2);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));

    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(1, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(10, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddItemWith1Apple1Cola1Apple() {
    String appleID = "1337";
    String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addItem(colaID);
    actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(1);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    itemRow = new ItemRow(cola);
    expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(3, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(45, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddQuantityWithNoItem() {
    String itemID = null;
    //String colaID = "1";
    SaleDTO actual = new SaleDTO(sale);
    actual = sale.addQuantity(5);
    //actual = sale.addItem(colaID);
    //actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    //ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    //ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    //ItemRow itemRow = new ItemRow(apple);
    //itemRow.increaseQuantity(1);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    //itemRow = new ItemRow(cola);
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(0, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddQuantityWithNullItem() {
    String itemID = null;
    //String colaID = "1";
    SaleDTO actual = new SaleDTO(sale);
    try {
      actual = sale.addItem(itemID);
    } catch (Exception e) {
      //TODO: handle exception
    }
    
    actual = sale.addQuantity(5);
    //actual = sale.addItem(colaID);
    //actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    //ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    //ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    //ItemRow itemRow = new ItemRow(apple);
    //itemRow.increaseQuantity(1);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    //itemRow = new ItemRow(cola);
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(0, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddQuantityWithApple() {
    String appleID = "1337";
    //String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addQuantity(5);
    //actual = sale.addItem(colaID);
    //actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    //ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(4);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    //itemRow = new ItemRow(cola);
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(5, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(50, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddQuantityWithSingleAppleAddedTwice() {
    String appleID = "1337";
    //String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addItem(appleID);
    actual = sale.addQuantity(5);
    //actual = sale.addItem(colaID);
    //actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    //ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(5);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    //itemRow = new ItemRow(cola);
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(6, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(60, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testEndSale() {
    String appleID = "1337";
    //String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addItem(appleID);
    actual = sale.addQuantity(5);
    actual = sale.endSale();
    //actual = sale.addItem(colaID);
    //actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    //ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(5);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    //itemRow = new ItemRow(cola);
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(6, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(60, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddDiscountWith10ApplesAndNullMember() {
    String appleID = "1337";
    //String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addQuantity(10);
    actual = sale.addDiscount(null);
    //actual = sale.addItem(colaID);
    //actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    //ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(20);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    //itemRow = new ItemRow(cola);
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(10, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(80, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddDiscountWith10ApplesAndMember() {
    String appleID = "1337";
    //String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addQuantity(10);
    actual = sale.addDiscount("1");
    //actual = sale.addItem(colaID);
    //actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    //ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(30);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    //itemRow = new ItemRow(cola);
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(10, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(70, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddDiscountWith10ApplesAndMemberWithSpecialDiscount() {
    String appleID = "1337";
    //String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addQuantity(10);
    actual = sale.addDiscount("1337");
    //actual = sale.addItem(colaID);
    //actual = sale.addItem(appleID);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    //assertEquals(sale.getLastItemFound(), actual.getLastItemFound(), "The LastItemFound is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    //ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(40);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    //itemRow = new ItemRow(cola);
    //expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(10, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(60, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testAddDiscountWith10ApplesAnd5Colas() {
    String appleID = "1337";
    String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addQuantity(10);
    actual = sale.addItem(colaID);
    actual = sale.addQuantity(5);
    actual = sale.addDiscount("1337");
    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(40);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    itemRow = new ItemRow(cola);
    itemRow.increaseQuantity(4);
    itemRow.setDiscount(50);
    expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(20, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(15, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(115, actual.getRunningTotal(), "The RunningTotal is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testLogSaleWith10AppleAnd5Cola() {
    String appleID = "1337";
    String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addQuantity(10);
    actual = sale.addItem(colaID);
    actual = sale.addQuantity(5);
    actual = sale.addDiscount("1337");
    actual = sale.logSale();

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(40);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    itemRow = new ItemRow(cola);
    itemRow.increaseQuantity(4);
    itemRow.setDiscount(50);
    expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(20, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(15, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(115, actual.getRunningTotal(), "The RunningTotal is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }

  @Test
  public void testPrintReceiptWith10ApplesAnd5Colas() {
    String appleID = "1337";
    String colaID = "1";
    SaleDTO actual = sale.addItem(appleID);
    actual = sale.addQuantity(10);
    actual = sale.addItem(colaID);
    actual = sale.addQuantity(5);
    actual = sale.addDiscount("1337");

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(sale.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(sale.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(sale.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(sale.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(sale.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(40);
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(itemRow));
    itemRow = new ItemRow(cola);
    itemRow.increaseQuantity(4);
    itemRow.setDiscount(50);
    expectedItemRows.add(new ItemRowDTO(itemRow));
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(20, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(15, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(115, actual.getRunningTotal(), "The RunningTotal is not what was expected: ");
    assertEquals(expectedItemRows.size(), actual.getItemRows().size(), "The size of SaleDTO ArrayList of ItemRowDTOs is not what was expected: ");
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expectedItemRows.get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expectedItemRows.get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }
  }
}
