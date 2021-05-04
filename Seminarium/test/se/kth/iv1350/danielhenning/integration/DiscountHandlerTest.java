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
    System.out.println("Start Up!");
    discountHandler = new DiscountHandler();
  }

  @AfterEach
  public void tearDown() {
    System.out.println("Tear Down!");
    discountHandler = null;
  }

  @Test
  public void testGetDiscountForAppleAndNotMember () {
    System.out.println("testGetDiscountForAppleAndNotMember");
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

    assertEquals(expected.size(), actual.size());
    assertEquals(expected.get(0).getClubMemberID(), actual.get(0).getClubMemberID());
    assertEquals(expected.get(0).getDiscountAmount(), actual.get(0).getDiscountAmount());
    assertEquals(expected.get(0).getItemIdentifier(), actual.get(0).getItemIdentifier());
    assertEquals(expected.get(0).getQuantityToGetDiscount(), actual.get(0).getQuantityToGetDiscount());
  }

  @Test
  public void testGetDiscountForAppleAndNotSpecificMember () {
    System.out.println("testGetDiscountForAppleAndNotSpecificMember");
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
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier());
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount());
    }
  }

  @Test
  public void testGetDiscountForAppleAndWithSpecificMember () {
    System.out.println("testGetDiscountForAppleAndWithSpecificMember");
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
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier());
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount());
    }
  }

  @Test
  public void testGetDiscountForColaAndNotMember () {
    System.out.println("testGetDiscountForColaAndNotMember");
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
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier());
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount());
    }
  }

  @Test
  public void testGetDiscountForColaAndMember () {
    System.out.println("testGetDiscountForColaAndNotMember");
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
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier());
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount());
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndNotMemberAndRunningTotal100() {
    System.out.println("testGetTotalSaleDiscountRulesAndNotMemberAndRunningTotal100");
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("");
    double runningTotal = 100;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    for(int i = 0; i < actual.size(); i++) {
      System.out.println(actual.get(i).getDiscountAmount());
    }
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount());
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndMemberAndRunningTotal100() {
    System.out.println("testGetTotalSaleDiscountRulesAndMemberAndRunningTotal100");
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("1");
    double runningTotal = 100;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));
    expected.add(new DiscountRulesTotalDTO(100,10,"0"));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    for(int i = 0; i < actual.size(); i++) {
      System.out.println(actual.get(i).getDiscountAmount());
    }
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount());
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndMember1337AndRunningTotal100() {
    System.out.println("testGetTotalSaleDiscountRulesAndMember1337AndRunningTotal100");
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("1337");
    double runningTotal = 100;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));
    expected.add(new DiscountRulesTotalDTO(100,10,"0"));
    expected.add(new DiscountRulesTotalDTO(100,20,"1337"));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    for(int i = 0; i < actual.size(); i++) {
      System.out.println(actual.get(i).getDiscountAmount());
    }
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount());
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndNotMemberAndRunningTotal500() {
    System.out.println("testGetTotalSaleDiscountRulesAndNotMemberAndRunningTotal500");
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("");
    double runningTotal = 500;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    for(int i = 0; i < actual.size(); i++) {
      System.out.println(actual.get(i).getDiscountAmount());
    }
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount());
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndMemberAndRunningTotal500() {
    System.out.println("testGetTotalSaleDiscountRulesAndMemberAndRunningTotal500");
    HandlerCreator handler = new HandlerCreator();
    ClubMemberDTO member = handler.getMemberHandler().getMember("1");
    double runningTotal = 500;

    ArrayList<DiscountRulesTotalDTO> expected = new ArrayList<DiscountRulesTotalDTO>();
    expected.add(new DiscountRulesTotalDTO(100,5,null));
    expected.add(new DiscountRulesTotalDTO(100,10,"0"));

    ArrayList<DiscountRulesTotalDTO> actual;
    actual = discountHandler.getTotalSaleDiscountRules(runningTotal, member);

    for(int i = 0; i < actual.size(); i++) {
      System.out.println(actual.get(i).getDiscountAmount());
    }
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount());
    }
  }

  @Test
  public void testGetTotalSaleDiscountRulesAndMember1337AndRunningTotal500() {
    System.out.println("testGetTotalSaleDiscountRulesAndMember1337AndRunningTotal500");
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

    for(int i = 0; i < actual.size(); i++) {
      System.out.println(actual.get(i).getDiscountAmount());
    }
    
    assertEquals(expected.size(), actual.size());
    for(int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID());
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount());
      assertEquals(expected.get(i).getAmountTotalToGetDiscount(), actual.get(i).getAmountTotalToGetDiscount());
    }
  }
}
