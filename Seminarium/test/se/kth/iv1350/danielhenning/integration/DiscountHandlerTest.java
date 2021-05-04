package se.kth.iv1350.danielhenning.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesTotalDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.model.ItemRow;

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
  public void testGetDiscountForNoneExistingItemAndNoneExistingMember() {
    HandlerCreator handler = new HandlerCreator();
    String itemIdentifier = "100";
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(item);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    ClubMemberDTO member = handler.getMemberHandler().getMember("");

    ArrayList<DiscountRulesDTO> expected = new ArrayList<DiscountRulesDTO>();

    ArrayList<DiscountRulesDTO> actual = discountHandler.getDiscountRules(itemRowDTO, member);

    assertEquals(expected, actual, "Given array of Discount Rules is not empty");
  }

  @Test
  public void testGetDiscountForNoneExistingItemAndExistingMember1337() {
    HandlerCreator handler = new HandlerCreator();
    String itemIdentifier = "100";
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(item);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    ClubMemberDTO member = handler.getMemberHandler().getMember("1337");

    ArrayList<DiscountRulesDTO> expected = new ArrayList<DiscountRulesDTO>();

    ArrayList<DiscountRulesDTO> actual = discountHandler.getDiscountRules(itemRowDTO, member);

    assertEquals(expected, actual, "Given array of Discount Rules is not empty");
  }

  @Test
  public void testGetDiscountForAppleAndNotMember() {
    HandlerCreator handler = new HandlerCreator();
    String itemIdentifier = "1337";
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(item);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    ClubMemberDTO member = handler.getMemberHandler().getMember("");

    ArrayList<DiscountRulesDTO> expected = new ArrayList<DiscountRulesDTO>();
    expected.add(new DiscountRulesDTO("1337", 10, 20, null));

    ArrayList<DiscountRulesDTO> actual;
    actual = discountHandler.getDiscountRules(itemRowDTO, member);

    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    assertEquals(expected.get(0).getClubMemberID(), actual.get(0).getClubMemberID(), "Given ClubMemberID is wrong: ");
    assertEquals(expected.get(0).getDiscountAmount(), actual.get(0).getDiscountAmount(), "Given DiscountAmount is wrong: ");
    assertEquals(expected.get(0).getItemIdentifier(), actual.get(0).getItemIdentifier(), "Given ItemID is wrong: ");
    assertEquals(expected.get(0).getQuantityToGetDiscount(), actual.get(0).getQuantityToGetDiscount(), "Given QuantityToGetDiscount is wrong: ");
  }

  @Test
  public void testGetDiscountForAppleAndNotSpecificMember() {
    HandlerCreator handler = new HandlerCreator();
    String itemIdentifier = "1337";
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(item);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    ClubMemberDTO member = handler.getMemberHandler().getMember("1");

    ArrayList<DiscountRulesDTO> expected = new ArrayList<DiscountRulesDTO>();
    expected.add(new DiscountRulesDTO("1337", 10, 20, null));
    expected.add(new DiscountRulesDTO("1337", 10, 30, "0"));

    ArrayList<DiscountRulesDTO> actual;
    actual = discountHandler.getDiscountRules(itemRowDTO, member);
    
    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier(), "Given ItemID is wrong: ");
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount(), "Given QuantityToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetDiscountForAppleAndWithSpecificMember() {
    HandlerCreator handler = new HandlerCreator();
    String itemIdentifier = "1337";
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(item);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    ClubMemberDTO member = handler.getMemberHandler().getMember("1337");

    ArrayList<DiscountRulesDTO> expected = new ArrayList<DiscountRulesDTO>();
    expected.add(new DiscountRulesDTO("1337", 10, 20, null));
    expected.add(new DiscountRulesDTO("1337", 10, 30, "0"));
    expected.add(new DiscountRulesDTO("1337", 10, 40, "1337"));

    ArrayList<DiscountRulesDTO> actual;
    actual = discountHandler.getDiscountRules(itemRowDTO, member);
    
    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier(), "Given ItemID is wrong: ");
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount(), "Given QuantityToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetDiscountForColaAndNotMember () {
    HandlerCreator handler = new HandlerCreator();
    String itemIdentifier = "1";
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(item);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    ClubMemberDTO member = handler.getMemberHandler().getMember("");

    ArrayList<DiscountRulesDTO> expected = new ArrayList<DiscountRulesDTO>();
    expected.add(new DiscountRulesDTO("1", 5, 50, null));

    ArrayList<DiscountRulesDTO> actual;
    actual = discountHandler.getDiscountRules(itemRowDTO, member);
    
    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier(), "Given ItemID is wrong: ");
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount(), "Given QuantityToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetDiscountForColaAndMember () {
    HandlerCreator handler = new HandlerCreator();
    String itemIdentifier = "1";
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(item);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    ClubMemberDTO member = handler.getMemberHandler().getMember("1337");

    ArrayList<DiscountRulesDTO> expected = new ArrayList<DiscountRulesDTO>();
    expected.add(new DiscountRulesDTO("1", 5, 50, null));

    ArrayList<DiscountRulesDTO> actual;
    actual = discountHandler.getDiscountRules(itemRowDTO, member);
    
    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier(), "Given ItemID is wrong: ");
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount(), "Given QuantityToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndNotMemberAndRunningTotal100() {
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("");
    double runningTotal = 100;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount(), "Given AmountTotalToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndMemberAndRunningTotal100() {
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("1");
    double runningTotal = 100;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));
    expected.add(new DiscountRulesTotalDTO(100,10,"0"));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);
    
    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount(), "Given AmountTotalToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndMember1337AndRunningTotal100() {
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("1337");
    double runningTotal = 100;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));
    expected.add(new DiscountRulesTotalDTO(100,10,"0"));
    expected.add(new DiscountRulesTotalDTO(100,20,"1337"));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);
    
    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount(), "Given AmountTotalToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndNotMemberAndRunningTotal500() {
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("");
    double runningTotal = 500;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount(), "Given AmountTotalToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndMemberAndRunningTotal500() {
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("1");
    double runningTotal = 500;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));
    expected.add(new DiscountRulesTotalDTO(100,10,"0"));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount(), "Given AmountTotalToGetDiscount is wrong: ");
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndMember1337AndRunningTotal500() {
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("1337");
    double runningTotal = 500;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));
    expected.add(new DiscountRulesTotalDTO(100,10,"0"));
    expected.add(new DiscountRulesTotalDTO(100,20,"1337"));
    expected.add(new DiscountRulesTotalDTO(500,25,"1337"));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    assertEquals(expected.size(), actual.size(), "Size of given array is wrong: ");
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given ClubMemberID is wrong: ");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given DiscountAmount is wrong: ");
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount(), "Given AmountTotalToGetDiscount is wrong: ");
    }
  }
}
