package se.kth.iv1350.danielhenning.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.AllDiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;

public class DiscountHandlerTest {

  private DiscountHandler discountHandler;
  private HandlerCreator handler;
  

  @BeforeEach
  public void startUp() {
    discountHandler = new DiscountHandler();
    handler = new HandlerCreator();
  }

  @AfterEach
  public void tearDown() {
    discountHandler = null;
    handler=null;
  }

  @Test
  public void testGetDiscountForNoneExistingMember() {

    ClubMemberDTO member = handler.getMemberHandler().getMember("");
    ArrayList<AllDiscountRulesDTO> expected = new ArrayList<AllDiscountRulesDTO>();
    ArrayList<AllDiscountRulesDTO> actual = handler.getDiscountHandler().getAllDiscountRules(member);
    expected.add(new AllDiscountRulesDTO("item","1", 5, 0 , 50, null));
    expected.add(new AllDiscountRulesDTO("item","1337", 10, 0 , 20, null));
    expected.add(new AllDiscountRulesDTO("total",null,0,100,5,null));
    for(int i=0;i<actual.size();i++){
      assertEquals(expected.get(i).getType(), actual.get(i).getType(), "Given array of Discount Rules is not correct Type");
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier(), "Given array of Discount Rules is not correct Identifier");
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount(), "Given array of Discount Rules is not correct Qunatity");
      assertEquals(expected.get(i).getAmountToGetDiscount(), actual.get(i).getAmountToGetDiscount(), "Given array of Discount Rules is not correct Amount");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given array of Discount Rules is not correct DiscountAmount");
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given array of Discount Rules is not correct Member");
    }
    
  }

  @Test
  public void testGetDiscountExistingMember1337() {
    ClubMemberDTO member = handler.getMemberHandler().getMember("1337");
    ArrayList<AllDiscountRulesDTO> expected = new ArrayList<AllDiscountRulesDTO>();
    ArrayList<AllDiscountRulesDTO> actual = discountHandler.getAllDiscountRules(member);

    expected.add(new AllDiscountRulesDTO("item","1", 5, 0 , 50, null));
    expected.add(new AllDiscountRulesDTO("item","1337", 10, 0 , 20, null));
    expected.add(new AllDiscountRulesDTO("item","1337", 10, 0 , 30, "0"));
    expected.add(new AllDiscountRulesDTO("item","1337", 10, 0 , 40, "1337"));
    expected.add(new AllDiscountRulesDTO("total",null,0,100,5,null));
    expected.add(new AllDiscountRulesDTO("total",null,0,100,10,"0"));
    expected.add(new AllDiscountRulesDTO("total",null,0,100,20,"1337"));
    expected.add(new AllDiscountRulesDTO("total",null,0,500,25,"1337")); 
    
    for(int i=0;i<actual.size();i++){
      assertEquals(expected.get(i).getType(), actual.get(i).getType(), "Given array of Discount Rules is not correct Type");
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier(), "Given array of Discount Rules is not correct Identifier");
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount(), "Given array of Discount Rules is not correct Qunatity");
      assertEquals(expected.get(i).getAmountToGetDiscount(), actual.get(i).getAmountToGetDiscount(), "Given array of Discount Rules is not correct Amount");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given array of Discount Rules is not correct DiscountAmount");
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given array of Discount Rules is not correct Member");
    }
  }

  @Test
  public void testGetDiscountForNullMember() {
    ClubMemberDTO member = handler.getMemberHandler().getMember(null);
    ArrayList<AllDiscountRulesDTO> expected = new ArrayList<AllDiscountRulesDTO>();
    ArrayList<AllDiscountRulesDTO> actual = discountHandler.getAllDiscountRules(member);

    expected.add(new AllDiscountRulesDTO("item","1", 5, 0 , 50, null));
    expected.add(new AllDiscountRulesDTO("item","1337", 10, 0 , 20, null));
    expected.add(new AllDiscountRulesDTO("total",null,0,100,5,null));
    
    for(int i=0;i<actual.size();i++){
      assertEquals(expected.get(i).getType(), actual.get(i).getType(), "Given array of Discount Rules is not correct Type");
      assertEquals(expected.get(i).getItemIdentifier(), actual.get(i).getItemIdentifier(), "Given array of Discount Rules is not correct Identifier");
      assertEquals(expected.get(i).getQuantityToGetDiscount(), actual.get(i).getQuantityToGetDiscount(), "Given array of Discount Rules is not correct Qunatity");
      assertEquals(expected.get(i).getAmountToGetDiscount(), actual.get(i).getAmountToGetDiscount(), "Given array of Discount Rules is not correct Amount");
      assertEquals(expected.get(i).getDiscountAmount(), actual.get(i).getDiscountAmount(), "Given array of Discount Rules is not correct DiscountAmount");
      assertEquals(expected.get(i).getClubMemberID(), actual.get(i).getClubMemberID(), "Given array of Discount Rules is not correct Member");
    }
  }

}
