package se.kth.iv1350.danielhenning.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;

public class CashRegisterTest {

  CashRegister cashRegister;
  double initialBalance = 1000;

  @BeforeEach
  public void startUp() {
    System.out.println("Start up!");
    cashRegister = new CashRegister(initialBalance);
  }

  @AfterEach
  public void tearDown() {
    System.out.println("Tear down!");
    cashRegister = null;
  }

  @Test
  public void testInitialBalance() {
    System.out.println("Testing Initial Balance!");
    double expected = 1000;
    double actual = cashRegister.getBalance();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddPaymentWithNoItems() {
    System.out.println("Testing to Add Payment with No Items!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    SaleDTO saleDTO = new SaleDTO(sale);
    cashRegister.addPayment(saleDTO);

    double expected = initialBalance;
    double actual = cashRegister.getBalance();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddPaymentWith1Apple() {
    System.out.println("Testing to Add Payment with 1 Apple!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    String appleID = "1337";
    sale.addItem(appleID);
    SaleDTO saleDTO = new SaleDTO(sale);
    cashRegister.addPayment(saleDTO);

    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);

    double expected = initialBalance + (apple.getPrice() * 1);
    double actual = cashRegister.getBalance();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddPaymentWith1000Apple() {
    System.out.println("Testing to Add Payment with 1000 Apple!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    String appleID = "1337";
    sale.addItem(appleID);
    sale.addQuantity(1000);
    SaleDTO saleDTO = new SaleDTO(sale);
    cashRegister.addPayment(saleDTO);

    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);

    double expected = initialBalance + (apple.getPrice() * 1000);
    double actual = cashRegister.getBalance();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddPaymentWith1Cola() {
    System.out.println("Testing to Add Payment with 1 Cola!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    String colaID = "1";
    sale.addItem(colaID);
    SaleDTO saleDTO = new SaleDTO(sale);
    cashRegister.addPayment(saleDTO);

    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(colaID);

    double expected = initialBalance + (apple.getPrice() * 1);
    double actual = cashRegister.getBalance();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddPaymentWith1000Cola() {
    System.out.println("Testing to Add Payment with 1000 Cola!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    String colaID = "1";
    int quantity = 1000;
    sale.addItem(colaID);
    sale.addQuantity(quantity);
    SaleDTO saleDTO = new SaleDTO(sale);
    cashRegister.addPayment(saleDTO);

    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(colaID);

    double expected = initialBalance + (apple.getPrice() * quantity);
    double actual = cashRegister.getBalance();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddPaymentWith1Apple1Cola() {
    System.out.println("Testing to Add Payment with 1 Apple and 1 Cola!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    String appleID = "1337";
    String colaID = "1";
    sale.addItem(appleID);
    sale.addItem(colaID);
    SaleDTO saleDTO = new SaleDTO(sale);
    cashRegister.addPayment(saleDTO);

    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);

    double expected = initialBalance + apple.getPrice() + cola.getPrice();
    double actual = cashRegister.getBalance();
    assertEquals(expected, actual);
  }

  @Test
  public void testAddPaymentWith1000Apple1000Cola() {
    System.out.println("Testing to Add Payment with 1000 Apple and 1000 Cola!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    String appleID = "1337";
    String colaID = "1";
    int quantity = 1000;
    sale.addItem(appleID);
    sale.addQuantity(quantity);
    sale.addItem(colaID);
    sale.addQuantity(quantity);
    SaleDTO saleDTO = new SaleDTO(sale);
    cashRegister.addPayment(saleDTO);

    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);
    ItemInformationDTO cola = handler.getInventoryHandler().getItemInformation(colaID);

    double expected = initialBalance + (apple.getPrice() * quantity) + (cola.getPrice() * quantity);
    double actual = cashRegister.getBalance();
    assertEquals(expected, actual);
  }

  @Test
  public void testGetChangeWithNoItems() {
    System.out.println("Testing to Get Change with No Items!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    SaleDTO saleDTO = new SaleDTO(sale);

    double amountPaid = 0;

    double expected = amountPaid;
    double actual = cashRegister.getChange(amountPaid, saleDTO);
    assertEquals(expected, actual);
  }

  @Test
  public void testGetChangeWith1Apple() {
    System.out.println("Testing to Get Change with 1 Apple!");
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Sale sale = new Sale(handler, saleLog);
    String appleID = "1337";
    sale.addItem(appleID);
    SaleDTO saleDTO = new SaleDTO(sale);

    ItemInformationDTO apple = handler.getInventoryHandler().getItemInformation(appleID);

    double amountPaid = 100;

    double expected = amountPaid - apple.getPrice();
    double actual = cashRegister.getChange(amountPaid, saleDTO);
    assertEquals(expected, actual);
  }
  
}
