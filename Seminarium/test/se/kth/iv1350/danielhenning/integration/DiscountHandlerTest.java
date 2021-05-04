package se.kth.iv1350.danielhenning.integration;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesDTO;
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
  public void testGetDiscountForAppleAndNotMember () {
    HandlerCreator handler = new HandlerCreator();
    String itemIdentifier = "1337";
    ItemInformationDTO item = handler.getInventoryHandler().getItemInformation(itemIdentifier);
    ItemRow itemRow = new ItemRow(item);
    ItemRowDTO itemRowDTO = new ItemRowDTO(itemRow);
    ClubMemberDTO member = handler.getMemberHandler().getMember("");
    ArrayList<DiscountRulesDTO> discountRules;

    discountRules = discountHandler.getDiscountRules(itemRowDTO, member);

  }
  
}
