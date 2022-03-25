package se.kth.iv1350.danielhenning.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.CouldNotConnectToServerException;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.integration.ItemDoesNotExistException;
import se.kth.iv1350.danielhenning.model.CashRegister;
//import se.kth.iv1350.danielhenning.model.Discount;
import se.kth.iv1350.danielhenning.model.ItemRow;
import se.kth.iv1350.danielhenning.model.Sale;
import se.kth.iv1350.danielhenning.model.SaleLog;

public class ControllerTest {

  private Controller controller;
  //private Discount discount;
  private SaleLog saleLog;
  private HandlerCreator handler;

  @BeforeEach
  public void startUp() {
    handler = new HandlerCreator();
    saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    //discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    CashRegister cashRegister = new CashRegister(1000);
    controller = new Controller(handler, cashRegister);
  }

  @AfterEach
  public void tearDown() {
    controller = null;
  }


  @Test
  public void testAddItemWithNullString() {
    Sale expected = new Sale(handler, saleLog/*, discount*/);

    controller.startSale();
    SaleDTO actual = controller.endSale();
    try {
      actual = controller.addItem(null);
    } catch (Exception e) {
      //TODO: handle exception
    }
    


    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
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
  public void testAddItemWit1nApple() throws CouldNotConnectToServerException, ItemDoesNotExistException, ItemNotFoundException {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    expected.addItem("1337");
    controller.startSale();
    SaleDTO actual = controller.addItem("1337");

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation("1337");
    ItemRow itemRow = new ItemRow(apple);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    expectedItemRows.add(itemRowDTO);

    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(1, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(10, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
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
  public void testAddItemWit2AppleInRow() throws CouldNotConnectToServerException, ItemDoesNotExistException, ItemNotFoundException {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    expected.addItem("1337");
    expected.addItem("1337");
    controller.startSale();
    SaleDTO actual = controller.addItem("1337");
    actual = controller.addItem("1337");

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation("1337");
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(1);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    expectedItemRows.add(itemRowDTO);
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(2, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(20, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
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
  public void testAddQuantityOnNullItem() {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    try {
      expected.addItem(null);
    } catch (Exception e) {
      //TODO: handle exception
    }
    

    expected.addQuantity(2);
    controller.startSale();
    SaleDTO actual;
    try {
      actual = controller.addItem(null);
    } catch (Exception e) {
      //TODO: handle exception
    }
    
    actual = controller.addQuantity(2);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
   // ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation("1337");
   // ItemRow itemRow = new ItemRow(apple);
   // itemRow.increaseQuantity(1);
   // ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    //expectedItemRows.add(itemRowDTO);
    
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
  public void testAddQuantityOnApple() throws CouldNotConnectToServerException, ItemDoesNotExistException, ItemNotFoundException {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    expected.addItem("1337");
    expected.addQuantity(2);
    controller.startSale();
    SaleDTO actual = controller.addItem("1337");
    actual = controller.addQuantity(2);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation("1337");
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(1);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    expectedItemRows.add(itemRowDTO);
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(2, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(20, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
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
  public void testAddNegativeQuantityOnApple() throws CouldNotConnectToServerException, ItemDoesNotExistException, ItemNotFoundException {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    expected.addItem("1337");
    expected.addQuantity(-10);
    controller.startSale();
    SaleDTO actual = controller.addItem("1337");
    actual = controller.addQuantity(-10);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation("1337");
    ItemRow itemRow = new ItemRow(apple);
    //itemRow.increaseQuantity(1);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    expectedItemRows.add(itemRowDTO);
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(1, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(10, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
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
  public void testEndSale() throws CouldNotConnectToServerException, ItemDoesNotExistException, ItemNotFoundException {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    expected.addItem("1337");
    expected.addQuantity(10);
    expected.endSale();
    controller.startSale();
    SaleDTO actual = controller.addItem("1337");
    actual = controller.addQuantity(10);
    actual = controller.endSale();

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation("1337");
    ItemRow itemRow = new ItemRow(apple);
    itemRow.increaseQuantity(9);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    expectedItemRows.add(itemRowDTO);
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(0, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(10, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(100, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
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
  public void testRequestDiscountWithNullItemAndNullCustomerID() {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    controller.startSale();
    SaleDTO actual =null;
    try {
      actual = controller.addItem(null);
    } catch (Exception e) {
      //TODO: handle exception
    }
  
    actual = controller.requestDiscount(null);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    //ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation("1337");
    //ItemRow itemRow = new ItemRow(apple);
    //itemRow.increaseQuantity(9);
    //ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    //expectedItemRows.add(itemRowDTO);
    
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
  public void testRequestDiscountWith10ColaAndNullCustomerID() throws CouldNotConnectToServerException, ItemDoesNotExistException, ItemNotFoundException {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    expected.addItem("1");
    expected.addQuantity(10);
    expected.endSale();
    expected.addDiscount(null);
    controller.startSale();
    SaleDTO actual = controller.addItem("1");
    actual = controller.addQuantity(10);
    actual = controller.endSale();
    actual = controller.requestDiscount(null);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation("1");
    ItemRow itemRow = new ItemRow(cola);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(100);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    expectedItemRows.add(itemRowDTO);
    
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(0, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(5, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(10, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(0, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(145, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
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
  public void testPaymentWith10ColaAndNullCustomerID() throws CouldNotConnectToServerException, ItemDoesNotExistException, ItemNotFoundException {
    Sale expected = new Sale(handler, saleLog/*, discount*/);
    expected.addItem("1");
    expected.addQuantity(10);
    expected.endSale();
    expected.addDiscount(null);
    expected.logSale();
    expected.printReceipt(500, 355);
    controller.startSale();
    SaleDTO actual = controller.addItem("1");
    actual = controller.addQuantity(10);
    actual = controller.endSale();
    actual = controller.requestDiscount(null);
    actual = controller.payment(500);

    /*
    * Compare Sale to given SaleDTO
    */
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getChange(), actual.getChange(), "The Change is not what was expected: ");
    assertEquals(expected.getDiscountOnWholeSale(), actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(expected.getAmountPaid(), actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getRunningTotal(), actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
    assertEquals(expected.getItemList().getItemRows().size(), actual.getItemRows().size());
    for(int i = 0; i < actual.getItemRows().size(); i++) {
      assertEquals(expected.getItemList().getItemRows().get(i).getDiscount(), actual.getItemRows().get(i).getDiscount(), "The Discount on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getPriceIncludingDiscount(), actual.getItemRows().get(i).getPriceIncludingDiscount(), "The Price on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getQuantity(), actual.getItemRows().get(i).getQuantity(), "The Quantity on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemDescription(), actual.getItemRows().get(i).getItem().getItemDescription(), "The ItemDescription on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getItemIdentifier(), actual.getItemRows().get(i).getItem().getItemIdentifier(), "The ItemID on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getPrice(), actual.getItemRows().get(i).getItem().getPrice(), "The ItemPrice on row " + i + " is not what was expected: ");
      assertEquals(expected.getItemList().getItemRows().get(i).getItem().getVATrate(), actual.getItemRows().get(i).getItem().getVATrate(), "The ItemVATrate on row " + i + " is not what was expected: ");
    }

    /**
     * Compare SaleDTO to expected values
     */
    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation("1");
    ItemRow itemRow = new ItemRow(cola);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(100);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    expectedItemRows.add(itemRowDTO);
    
    assertEquals(500, actual.getAmountPaid(), "The AmountPaid is not what was expected: ");
    assertEquals(355, actual.getChange(), "The Change is not what was expected: ");
    assertEquals(5, actual.getDiscountOnWholeSale(), "The DiscountOnWholeSale is not what was expected: ");
    assertEquals(10, actual.getNumberOfItems(), "The NumberOfItems is not what was expected: ");
    assertEquals(145, actual.getRunningTotal(), "The AmountPaid is not what was expected: ");
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
}