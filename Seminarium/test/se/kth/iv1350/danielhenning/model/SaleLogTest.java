package se.kth.iv1350.danielhenning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.AccountingHandler;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.integration.InventoryHandler;

public class SaleLogTest {

  private SaleLog saleLog;

  @BeforeEach
  public void startUp() {
    AccountingHandler accountingHandler = new AccountingHandler();
    InventoryHandler inventoryHandler = new InventoryHandler();
    saleLog = new SaleLog(accountingHandler, inventoryHandler);
  }

  @AfterEach
  public void tearDown() {
    saleLog = null;
  }

  @Test
  public void testLogSaleWithoutItems() {
    HandlerCreator handler = new HandlerCreator();
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    SaleDTO saleDTO = new SaleDTO(sale);
    saleLog.logSale(saleDTO);

    ArrayList<SaleDTO> todaysSales = new ArrayList<SaleDTO>();

    assertEquals(0, saleLog.getAoumtSoldForToday(), "The AmountSoldForToday is not what was expected: ");
    assertEquals(0, saleLog.getAmountDiscountGivenToday(), "The AmountDiscountGivenToday is not what was expected: ");
    assertEquals(todaysSales.size(), saleLog.getTodaysSales().size(), "Size of SaleLogs ArrayList todaysSale is not what was expected: ");
    for(int i = 0; i < saleLog.getTodaysSales().size(); i++) {
      assertEquals(todaysSales.get(i).getAmountPaid(), saleLog.getTodaysSales().get(i).getAmountPaid(), "SaleLogs AmountPaid is not what was expected: ");
      assertEquals(todaysSales.get(i).getChange(), saleLog.getTodaysSales().get(i).getChange(), "SaleLogs Change is not what was expected: ");
      assertEquals(todaysSales.get(i).getDiscountOnWholeSale(), saleLog.getTodaysSales().get(i).getDiscountOnWholeSale(), "SaleLogs DiscountOnWholeSale is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfItems(), saleLog.getTodaysSales().get(i).getNumberOfItems(), "SaleLogs NumberOfItems is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfRows(), saleLog.getTodaysSales().get(i).getNumberOfRows(), "SaleLogs NumberOfRows is not what was expected: ");
      assertEquals(todaysSales.get(i).getRunningTotal(), saleLog.getTodaysSales().get(i).getRunningTotal(), "SaleLogs RunningTotal is not what was expected: ");
    }
  }

  @Test
  public void testLogSaleWith1Apple() {
    HandlerCreator handler = new HandlerCreator();
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    sale.addItem("1337");
    SaleDTO saleDTO = new SaleDTO(sale);
    saleLog.logSale(saleDTO);

    ArrayList<SaleDTO> todaysSales = new ArrayList<SaleDTO>();
    todaysSales.add(saleDTO);

    assertEquals(10, saleLog.getAoumtSoldForToday(), "The AmountSoldForToday is not what was expected: ");
    assertEquals(0, saleLog.getAmountDiscountGivenToday(), "The AmountDiscountGivenToday is not what was expected: ");
    assertEquals(todaysSales.size(), saleLog.getTodaysSales().size(), "Size of SaleLogs ArrayList todaysSale is not what was expected: ");
    for(int i = 0; i < saleLog.getTodaysSales().size(); i++) {
      assertEquals(todaysSales.get(i).getAmountPaid(), saleLog.getTodaysSales().get(i).getAmountPaid(), "SaleLogs AmountPaid is not what was expected: ");
      assertEquals(todaysSales.get(i).getChange(), saleLog.getTodaysSales().get(i).getChange(), "SaleLogs Change is not what was expected: ");
      assertEquals(todaysSales.get(i).getDiscountOnWholeSale(), saleLog.getTodaysSales().get(i).getDiscountOnWholeSale(), "SaleLogs DiscountOnWholeSale is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfItems(), saleLog.getTodaysSales().get(i).getNumberOfItems(), "SaleLogs NumberOfItems is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfRows(), saleLog.getTodaysSales().get(i).getNumberOfRows(), "SaleLogs NumberOfRows is not what was expected: ");
      assertEquals(todaysSales.get(i).getRunningTotal(), saleLog.getTodaysSales().get(i).getRunningTotal(), "SaleLogs RunningTotal is not what was expected: ");
    }
  }

  @Test
  public void testLogSaleWith10Apple() {
    HandlerCreator handler = new HandlerCreator();
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    sale.addItem("1337");
    sale.addQuantity(10);
    SaleDTO saleDTO = new SaleDTO(sale);
    saleLog.logSale(saleDTO);

    ArrayList<SaleDTO> todaysSales = new ArrayList<SaleDTO>();
    todaysSales.add(saleDTO);

    assertEquals(100, saleLog.getAoumtSoldForToday(), "The AmountSoldForToday is not what was expected: ");
    assertEquals(0, saleLog.getAmountDiscountGivenToday(), "The AmountDiscountGivenToday is not what was expected: ");
    assertEquals(todaysSales.size(), saleLog.getTodaysSales().size(), "Size of SaleLogs ArrayList todaysSale is not what was expected: ");
    for(int i = 0; i < saleLog.getTodaysSales().size(); i++) {
      assertEquals(todaysSales.get(i).getAmountPaid(), saleLog.getTodaysSales().get(i).getAmountPaid(), "SaleLogs AmountPaid is not what was expected: ");
      assertEquals(todaysSales.get(i).getChange(), saleLog.getTodaysSales().get(i).getChange(), "SaleLogs Change is not what was expected: ");
      assertEquals(todaysSales.get(i).getDiscountOnWholeSale(), saleLog.getTodaysSales().get(i).getDiscountOnWholeSale(), "SaleLogs DiscountOnWholeSale is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfItems(), saleLog.getTodaysSales().get(i).getNumberOfItems(), "SaleLogs NumberOfItems is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfRows(), saleLog.getTodaysSales().get(i).getNumberOfRows(), "SaleLogs NumberOfRows is not what was expected: ");
      assertEquals(todaysSales.get(i).getRunningTotal(), saleLog.getTodaysSales().get(i).getRunningTotal(), "SaleLogs RunningTotal is not what was expected: ");
    }
  }

  @Test
  public void testLogSaleWith1Cola() {
    HandlerCreator handler = new HandlerCreator();
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    sale.addItem("1");
    SaleDTO saleDTO = new SaleDTO(sale);
    saleLog.logSale(saleDTO);

    ArrayList<SaleDTO> todaysSales = new ArrayList<SaleDTO>();
    todaysSales.add(saleDTO);

    assertEquals(25, saleLog.getAoumtSoldForToday(), "The AmountSoldForToday is not what was expected: ");
    assertEquals(0, saleLog.getAmountDiscountGivenToday(), "The AmountDiscountGivenToday is not what was expected: ");
    assertEquals(todaysSales.size(), saleLog.getTodaysSales().size(), "Size of SaleLogs ArrayList todaysSale is not what was expected: ");
    for(int i = 0; i < saleLog.getTodaysSales().size(); i++) {
      assertEquals(todaysSales.get(i).getAmountPaid(), saleLog.getTodaysSales().get(i).getAmountPaid(), "SaleLogs AmountPaid is not what was expected: ");
      assertEquals(todaysSales.get(i).getChange(), saleLog.getTodaysSales().get(i).getChange(), "SaleLogs Change is not what was expected: ");
      assertEquals(todaysSales.get(i).getDiscountOnWholeSale(), saleLog.getTodaysSales().get(i).getDiscountOnWholeSale(), "SaleLogs DiscountOnWholeSale is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfItems(), saleLog.getTodaysSales().get(i).getNumberOfItems(), "SaleLogs NumberOfItems is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfRows(), saleLog.getTodaysSales().get(i).getNumberOfRows(), "SaleLogs NumberOfRows is not what was expected: ");
      assertEquals(todaysSales.get(i).getRunningTotal(), saleLog.getTodaysSales().get(i).getRunningTotal(), "SaleLogs RunningTotal is not what was expected: ");
    }
  }

  @Test
  public void testLogSaleWith10Cola() {
    HandlerCreator handler = new HandlerCreator();
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    sale.addItem("1");
    sale.addQuantity(10);
    SaleDTO saleDTO = new SaleDTO(sale);
    saleLog.logSale(saleDTO);

    ArrayList<SaleDTO> todaysSales = new ArrayList<SaleDTO>();
    todaysSales.add(saleDTO);

    assertEquals(250, saleLog.getAoumtSoldForToday(), "The AmountSoldForToday is not what was expected: ");
    assertEquals(0, saleLog.getAmountDiscountGivenToday(), "The AmountDiscountGivenToday is not what was expected: ");
    assertEquals(todaysSales.size(), saleLog.getTodaysSales().size(), "Size of SaleLogs ArrayList todaysSale is not what was expected: ");
    for(int i = 0; i < saleLog.getTodaysSales().size(); i++) {
      assertEquals(todaysSales.get(i).getAmountPaid(), saleLog.getTodaysSales().get(i).getAmountPaid(), "SaleLogs AmountPaid is not what was expected: ");
      assertEquals(todaysSales.get(i).getChange(), saleLog.getTodaysSales().get(i).getChange(), "SaleLogs Change is not what was expected: ");
      assertEquals(todaysSales.get(i).getDiscountOnWholeSale(), saleLog.getTodaysSales().get(i).getDiscountOnWholeSale(), "SaleLogs DiscountOnWholeSale is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfItems(), saleLog.getTodaysSales().get(i).getNumberOfItems(), "SaleLogs NumberOfItems is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfRows(), saleLog.getTodaysSales().get(i).getNumberOfRows(), "SaleLogs NumberOfRows is not what was expected: ");
      assertEquals(todaysSales.get(i).getRunningTotal(), saleLog.getTodaysSales().get(i).getRunningTotal(), "SaleLogs RunningTotal is not what was expected: ");
    }
  }

  @Test
  public void testLogSaleWith10ColaAndDiscountWithMember() {
    HandlerCreator handler = new HandlerCreator();
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    sale.addItem("1");
    sale.addQuantity(10);
    sale.addDiscount("1337");
    SaleDTO saleDTO = new SaleDTO(sale);
    saleLog.logSale(saleDTO);

    ArrayList<SaleDTO> todaysSales = new ArrayList<SaleDTO>();
    todaysSales.add(saleDTO);

    assertEquals(130, saleLog.getAoumtSoldForToday(), "The AmountSoldForToday is not what was expected: ");
    assertEquals(120, saleLog.getAmountDiscountGivenToday(), "The AmountDiscountGivenToday is not what was expected: ");
    assertEquals(todaysSales.size(), saleLog.getTodaysSales().size(), "Size of SaleLogs ArrayList todaysSale is not what was expected: ");
    for(int i = 0; i < saleLog.getTodaysSales().size(); i++) {
      assertEquals(todaysSales.get(i).getAmountPaid(), saleLog.getTodaysSales().get(i).getAmountPaid(), "SaleLogs AmountPaid is not what was expected: ");
      assertEquals(todaysSales.get(i).getChange(), saleLog.getTodaysSales().get(i).getChange(), "SaleLogs Change is not what was expected: ");
      assertEquals(todaysSales.get(i).getDiscountOnWholeSale(), saleLog.getTodaysSales().get(i).getDiscountOnWholeSale(), "SaleLogs DiscountOnWholeSale is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfItems(), saleLog.getTodaysSales().get(i).getNumberOfItems(), "SaleLogs NumberOfItems is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfRows(), saleLog.getTodaysSales().get(i).getNumberOfRows(), "SaleLogs NumberOfRows is not what was expected: ");
      assertEquals(todaysSales.get(i).getRunningTotal(), saleLog.getTodaysSales().get(i).getRunningTotal(), "SaleLogs RunningTotal is not what was expected: ");
    }
  }

  @Test
  public void testLogSaleWithNoneExistingItem() {
    HandlerCreator handler = new HandlerCreator();
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    sale.addItem("10");
    SaleDTO saleDTO = new SaleDTO(sale);
    saleLog.logSale(saleDTO);

    ArrayList<SaleDTO> todaysSales = new ArrayList<SaleDTO>();

    assertEquals(0, saleLog.getAoumtSoldForToday(), "The AmountSoldForToday is not what was expected: ");
    assertEquals(0, saleLog.getAmountDiscountGivenToday(), "The AmountDiscountGivenToday is not what was expected: ");
    assertEquals(todaysSales.size(), saleLog.getTodaysSales().size(), "Size of SaleLogs ArrayList todaysSale is not what was expected: ");
    for(int i = 0; i < saleLog.getTodaysSales().size(); i++) {
      assertEquals(todaysSales.get(i).getAmountPaid(), saleLog.getTodaysSales().get(i).getAmountPaid(), "SaleLogs AmountPaid is not what was expected: ");
      assertEquals(todaysSales.get(i).getChange(), saleLog.getTodaysSales().get(i).getChange(), "SaleLogs Change is not what was expected: ");
      assertEquals(todaysSales.get(i).getDiscountOnWholeSale(), saleLog.getTodaysSales().get(i).getDiscountOnWholeSale(), "SaleLogs DiscountOnWholeSale is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfItems(), saleLog.getTodaysSales().get(i).getNumberOfItems(), "SaleLogs NumberOfItems is not what was expected: ");
      assertEquals(todaysSales.get(i).getNumberOfRows(), saleLog.getTodaysSales().get(i).getNumberOfRows(), "SaleLogs NumberOfRows is not what was expected: ");
      assertEquals(todaysSales.get(i).getRunningTotal(), saleLog.getTodaysSales().get(i).getRunningTotal(), "SaleLogs RunningTotal is not what was expected: ");
    }
  }
  
}
