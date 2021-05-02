package se.kth.iv1350.danielhenning.integration;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesTotalDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;

/**
 * The DiscountHandler class represents the system
 * that applies discounts to sales depending on
 * the current discount rules
 */
public class DiscountHandler {

  private ArrayList<DiscountRulesDTO> discountRulesArray;
  private ArrayList<DiscountRulesTotalDTO> discountRulesTotalArray;

  /**
   * Creates a new instance of the class DiscountHandler
   */
  public DiscountHandler() {

    this.discountRulesArray = new ArrayList<DiscountRulesDTO>();
    this.discountRulesArray.add(new DiscountRulesDTO("1", 5, 50, null));
    this.discountRulesArray.add(new DiscountRulesDTO("1337", 10, 20, null));
    this.discountRulesArray.add(new DiscountRulesDTO("1337", 10, 30, "0"));
    this.discountRulesArray.add(new DiscountRulesDTO("1337", 10, 40, "1337"));
    this.discountRulesArray.add(new DiscountRulesDTO("1337", 10, 60, "5020"));
    
    this.discountRulesTotalArray = new ArrayList<DiscountRulesTotalDTO>();
    this.discountRulesTotalArray.add(new DiscountRulesTotalDTO(100,5,null));
    this.discountRulesTotalArray.add(new DiscountRulesTotalDTO(100,10,"0"));
    this.discountRulesTotalArray.add(new DiscountRulesTotalDTO(100,20,"1337"));
    this.discountRulesTotalArray.add(new DiscountRulesTotalDTO(500,25,"1337"));    
  }

  /**
   * This funktion imitates what data a SQL queery should have retrived.
   * @param item is a ItemRowDTO that is going to be checked against database.
   * @param member is ClubMemberDTo with the info on the costumer to be checked against database.
   */
  public ArrayList<DiscountRulesDTO> getDiscountRules(ItemRowDTO item, ClubMemberDTO member){
    ArrayList<DiscountRulesDTO> discountRulesToSend = new ArrayList<DiscountRulesDTO>();

    String giveItemID = item.getitem().getItemIdentifier();
    String givenMemberID = member.getMemberID();
    String itemRuleID;
    String memberRuleID;

    for(int i = 0; i < discountRulesArray.size(); i++) {
      itemRuleID = discountRulesArray.get(i).getItemIdentifier();
      memberRuleID = discountRulesArray.get(i).getClubMemberID();

      if(itemRuleID == giveItemID && memberRuleID == null){
        discountRulesToSend.add(discountRulesArray.get(i));
      }
      if(itemRuleID == giveItemID && memberRuleID == "0" && givenMemberID != null){
        discountRulesToSend.add(discountRulesArray.get(i));
      }
      if(itemRuleID == giveItemID && memberRuleID== givenMemberID){
        discountRulesToSend.add(discountRulesArray.get(i));
      }
    }
   return discountRulesToSend;
  }

/**
 * This funktion imitates what data a SQL queery should have retrived.
 * @param runningTotal a double represtenting running total after item discount.
 * @param member a ClubMemberDTO of the customer.
 * @return returns and ArrayList of DiscountRulesTotalDTO with all the applicible rules for that costumer.
 */
  public ArrayList<DiscountRulesTotalDTO> getDiscountTotalRules(double runningTotal, ClubMemberDTO member){
    ArrayList<DiscountRulesTotalDTO> discountRulesTotalToSend = new ArrayList<DiscountRulesTotalDTO>();

    double amountToGetDiscont;
    String memberToGetDiscount;

    for(int i = 0; i < discountRulesTotalArray.size(); i++){

      amountToGetDiscont = discountRulesTotalArray.get(i).getAmountTotalToGetDiscount();
      memberToGetDiscount = discountRulesTotalArray.get(i).getClubMemberID();
     
      if(runningTotal > amountToGetDiscont && memberToGetDiscount == null){
        discountRulesTotalToSend.add(discountRulesTotalArray.get(i));
      }
     
      if(runningTotal > amountToGetDiscont && memberToGetDiscount == "0" && member.getMemberID() != null){
        discountRulesTotalToSend.add(discountRulesTotalArray.get(i));
      }

      if(runningTotal > amountToGetDiscont && memberToGetDiscount == member.getMemberID()){
        discountRulesTotalToSend.add(discountRulesTotalArray.get(i));
      }
    }
    return discountRulesTotalToSend;
  }
}