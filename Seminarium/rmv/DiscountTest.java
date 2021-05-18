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
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.DiscountHandler;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.integration.MemberHandler;

public class DiscountTest {

  private Discount discount;

  @BeforeEach
  public void startUp() {
    DiscountHandler discountHandler = new DiscountHandler();
    MemberHandler memberHandler = new MemberHandler();
    discount = new Discount(discountHandler, memberHandler);
  }

  @AfterEach
  public void tearDown() {
    discount = null;
  }

  @Test
  public void testGetDiscountDTOWithNoMemberIDAndNoSaleDTO() {
    String customerID = "";
    DiscountDTO expected = null;
    DiscountDTO actual = discount.getDiscountDTO(customerID, null);
    assertEquals(expected, actual, "The DiscountDTO is not what was expected: ");
  }

  @Test
  public void testGetDiscountDTOWithNoMemberIDAndSaleDTOWithoutItems() {
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    SaleDTO saleDTO = new SaleDTO(sale);

    String customerID = "";

    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ClubMemberDTO member = handler.getMemberHandler().getMember(customerID);

    DiscountDTO expected = new DiscountDTO(expectedItemRows, member, 0);
    DiscountDTO actual = discount.getDiscountDTO(customerID, saleDTO);
    assertEquals(expected.getClass(), actual.getClass(), "The DiscountDTO is not of expected class: ");
    assertEquals(expected.getItemRowDTO(), actual.getItemRowDTO(), "The DiscountDTO's ItemRows are not as expected: ");
    assertEquals(expected.getMember(), actual.getMember(), "The DiscountDTO's member is not what was expected: ");
    assertEquals(expected.getTotalSaleDiscount(), actual.getTotalSaleDiscount(), "The DiscountDTO's TotalSaleDiscount is not what was expected: ");
  }

  @Test
  public void testGetDiscountDTOWithMemberIDAndSaleDTOWithoutItems() {
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    SaleDTO saleDTO = new SaleDTO(sale);

    String customerID = "1337";

    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ClubMemberDTO member = handler.getMemberHandler().getMember(customerID);

    DiscountDTO expected = new DiscountDTO(expectedItemRows, member, 0);
    DiscountDTO actual = discount.getDiscountDTO(customerID, saleDTO);
    assertEquals(expected.getClass(), actual.getClass(), "The DiscountDTO is not of expected class: ");
    assertEquals(expected.getItemRowDTO(), actual.getItemRowDTO(), "The DiscountDTO's ItemRows are not as expected: ");
    assertEquals(expected.getMember().getMemberID(), actual.getMember().getMemberID(), "The DiscountDTO's member is not what was expected: ");
    assertEquals(expected.getTotalSaleDiscount(), actual.getTotalSaleDiscount(), "The DiscountDTO's TotalSaleDiscount is not what was expected: ");
  }

  @Test
  public void testGetDiscountDTOWithNoMemberIDAndSaleDTOWith1Apple() {
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    String itemIdentifier = "1337";
    sale.addItem(itemIdentifier);
    SaleDTO saleDTO = new SaleDTO(sale);

    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);

    String customerID = "";

    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(new ItemRow(item)));
    ClubMemberDTO member = handler.getMemberHandler().getMember(customerID);

    DiscountDTO expected = new DiscountDTO(expectedItemRows, member, 0);
    DiscountDTO actual = discount.getDiscountDTO(customerID, saleDTO);
    assertEquals(expected.getClass(), actual.getClass(), "The DiscountDTO is not of expected class: ");
    assertEquals(expected.getItemRowDTO().get(0).getItem().getItemIdentifier(), actual.getItemRowDTO().get(0).getItem().getItemIdentifier(), "The DiscountDTO's ItemRows are not as expected: ");
    assertEquals(expected.getMember(), actual.getMember(), "The DiscountDTO's member is not what was expected: ");
    assertEquals(expected.getTotalSaleDiscount(), actual.getTotalSaleDiscount(), "The DiscountDTO's TotalSaleDiscount is not what was expected: ");
  }

  @Test
  public void testGetDiscountDTOWithNoMemberIDAndSaleDTOWith10Apple() {
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    String itemIdentifier = "1337";
    sale.addItem(itemIdentifier);
    sale.addQuantity(10);
    SaleDTO saleDTO = new SaleDTO(sale);

    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);

    String customerID = "";

    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemRow itemRow = new ItemRow(item);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(20);
    expectedItemRows.add(new ItemRowDTO(itemRow));
    ClubMemberDTO member = handler.getMemberHandler().getMember(customerID);

    DiscountDTO expected = new DiscountDTO(expectedItemRows, member, 0);
    DiscountDTO actual = discount.getDiscountDTO(customerID, saleDTO);
    assertEquals(expected.getClass(), actual.getClass(), "The DiscountDTO is not of expected class: ");
    assertEquals(expected.getItemRowDTO().get(0).getItem().getItemIdentifier(), actual.getItemRowDTO().get(0).getItem().getItemIdentifier(), "The DiscountDTO's ItemRows are not as expected: ");
    assertEquals(expected.getMember(), actual.getMember(), "The DiscountDTO's member is not what was expected: ");
    assertEquals(expected.getTotalSaleDiscount(), actual.getTotalSaleDiscount(), "The DiscountDTO's TotalSaleDiscount is not what was expected: ");
    assertEquals(expected.getItemRowDTO().get(0).getDiscount(), actual.getItemRowDTO().get(0).getDiscount(), "The Discount on row 0 is not what was expected: ");
  }

  @Test
  public void testGetDiscountDTOWithMemberIDAndSaleDTOWith10Apple() {
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    String itemIdentifier = "1337";
    sale.addItem(itemIdentifier);
    sale.addQuantity(10);
    SaleDTO saleDTO = new SaleDTO(sale);

    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);

    String customerID = "1337";

    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemRow itemRow = new ItemRow(item);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(40);
    expectedItemRows.add(new ItemRowDTO(itemRow));
    ClubMemberDTO member = handler.getMemberHandler().getMember(customerID);

    DiscountDTO expected = new DiscountDTO(expectedItemRows, member, 0);
    DiscountDTO actual = discount.getDiscountDTO(customerID, saleDTO);
    assertEquals(expected.getClass(), actual.getClass(), "The DiscountDTO is not of expected class: ");
    assertEquals(expected.getItemRowDTO().get(0).getItem().getItemIdentifier(), actual.getItemRowDTO().get(0).getItem().getItemIdentifier(), "The DiscountDTO's ItemRows are not as expected: ");
    assertEquals(expected.getMember().getMemberID(), actual.getMember().getMemberID(), "The DiscountDTO's member is not what was expected: ");
    assertEquals(expected.getTotalSaleDiscount(), actual.getTotalSaleDiscount(), "The DiscountDTO's TotalSaleDiscount is not what was expected: ");
    assertEquals(expected.getItemRowDTO().get(0).getDiscount(), actual.getItemRowDTO().get(0).getDiscount(), "The Discount on row 0 is not what was expected: ");
  }

  @Test
  public void testGetDiscountDTOWithNoCustomerIDAndSaleDTOWith1Cola() {
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    String itemIdentifier = "1";
    sale.addItem(itemIdentifier);
    SaleDTO saleDTO = new SaleDTO(sale);

    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);

    String customerID = "";

    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    expectedItemRows.add(new ItemRowDTO(new ItemRow(item)));
    ClubMemberDTO member = handler.getMemberHandler().getMember(customerID);

    DiscountDTO expected = new DiscountDTO(expectedItemRows, member, 0);
    DiscountDTO actual = discount.getDiscountDTO(customerID, saleDTO);
    assertEquals(expected.getClass(), actual.getClass(), "The DiscountDTO is not of expected class: ");
    assertEquals(expected.getItemRowDTO().get(0).getItem().getItemIdentifier(), actual.getItemRowDTO().get(0).getItem().getItemIdentifier(), "The DiscountDTO's ItemRows are not as expected: ");
    assertEquals(expected.getMember(), actual.getMember(), "The DiscountDTO's member is not what was expected: ");
    assertEquals(expected.getTotalSaleDiscount(), actual.getTotalSaleDiscount(), "The DiscountDTO's TotalSaleDiscount is not what was expected: ");
  }

  @Test
  public void testGetDiscountDTOWithNoMemberIDAndSaleDTOWith10Cola() {
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    String itemIdentifier = "1";
    sale.addItem(itemIdentifier);
    sale.addQuantity(10);
    SaleDTO saleDTO = new SaleDTO(sale);

    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);

    String customerID = "";

    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemRow itemRow = new ItemRow(item);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(100);
    expectedItemRows.add(new ItemRowDTO(itemRow));
    ClubMemberDTO member = handler.getMemberHandler().getMember(customerID);

    DiscountDTO expected = new DiscountDTO(expectedItemRows, member, 5);
    DiscountDTO actual = discount.getDiscountDTO(customerID, saleDTO);
    assertEquals(expected.getClass(), actual.getClass(), "The DiscountDTO is not of expected class: ");
    assertEquals(expected.getItemRowDTO().get(0).getItem().getItemIdentifier(), actual.getItemRowDTO().get(0).getItem().getItemIdentifier(), "The DiscountDTO's ItemRows are not as expected: ");
    assertEquals(expected.getMember(), actual.getMember(), "The DiscountDTO's member is not what was expected: ");
    assertEquals(expected.getTotalSaleDiscount(), actual.getTotalSaleDiscount(), "The DiscountDTO's TotalSaleDiscount is not what was expected: ");
    assertEquals(expected.getItemRowDTO().get(0).getDiscount(), actual.getItemRowDTO().get(0).getDiscount(), "The Discount on row 0 is not what was expected: ");
  }

  @Test
  public void testGetDiscountDTONoMemberIDAndSaleDTOWith10Cola() {
    HandlerCreator handler = new HandlerCreator();
    SaleLog saleLog = new SaleLog(handler.getAccountingHandler(), handler.getInventoryHandler());
    Discount discount = new Discount(handler.getDiscountHandler(), handler.getMemberHandler());
    Sale sale = new Sale(handler, saleLog, discount);
    String itemIdentifier = "1";
    sale.addItem(itemIdentifier);
    sale.addQuantity(10);
    SaleDTO saleDTO = new SaleDTO(sale);

    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);

    String customerID = "1337";

    ArrayList<ItemRowDTO> expectedItemRows = new ArrayList<ItemRowDTO>();
    ItemRow itemRow = new ItemRow(item);
    itemRow.increaseQuantity(9);
    itemRow.setDiscount(100);
    expectedItemRows.add(new ItemRowDTO(itemRow));
    ClubMemberDTO member = handler.getMemberHandler().getMember(customerID);

    DiscountDTO expected = new DiscountDTO(expectedItemRows, member, 20);
    DiscountDTO actual = discount.getDiscountDTO(customerID, saleDTO);
    assertEquals(expected.getClass(), actual.getClass(), "The DiscountDTO is not of expected class: ");
    assertEquals(expected.getItemRowDTO().get(0).getItem().getItemIdentifier(), actual.getItemRowDTO().get(0).getItem().getItemIdentifier(), "The DiscountDTO's ItemRows are not as expected: ");
    assertEquals(expected.getMember().getMemberID(), actual.getMember().getMemberID(), "The DiscountDTO's member is not what was expected: ");
    assertEquals(expected.getTotalSaleDiscount(), actual.getTotalSaleDiscount(), "The DiscountDTO's TotalSaleDiscount is not what was expected: ");
    assertEquals(expected.getItemRowDTO().get(0).getDiscount(), actual.getItemRowDTO().get(0).getDiscount(), "The Discount on row 0 is not what was expected: ");
  }
}
