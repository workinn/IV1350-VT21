package se.kth.iv1350.danielhenning.integration;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.AllDiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;

/**
 * The DiscountHandler class represents the system
 * that applies discounts to sales depending on
 * the current discount rules
 */
public class DiscountHandler {

  private ArrayList<AllDiscountRulesDTO> allDiscountRules;
  /**
   * Creates a new instance of the class DiscountHandler
   */
  public DiscountHandler() {
    
    this.allDiscountRules = new ArrayList<AllDiscountRulesDTO>();
    this.allDiscountRules.add(new AllDiscountRulesDTO("item","1", 5, 0 , 50, null));
    this.allDiscountRules.add(new AllDiscountRulesDTO("item","1337", 10, 0 , 20, null));
    this.allDiscountRules.add(new AllDiscountRulesDTO("item","1337", 10, 0 , 30, "0"));
    this.allDiscountRules.add(new AllDiscountRulesDTO("item","1337", 10, 0 , 40, "1337"));
    this.allDiscountRules.add(new AllDiscountRulesDTO("total",null,0,100,5,null));
    this.allDiscountRules.add(new AllDiscountRulesDTO("total",null,0,100,10,"0"));
    this.allDiscountRules.add(new AllDiscountRulesDTO("total",null,0,100,20,"1337"));
    this.allDiscountRules.add(new AllDiscountRulesDTO("total",null,0,500,25,"1337"));  
    
  }

 
/**
 * Ths class returns a discount roles parameter list.
 * @param member the member dto
 * @return returns a list with all applicable discounts.
 */
public ArrayList<AllDiscountRulesDTO> getAllDiscountRules(ClubMemberDTO member){
  ArrayList<AllDiscountRulesDTO> discountRulesToReturn = new ArrayList<AllDiscountRulesDTO>();
  
  for(AllDiscountRulesDTO currentdiscountRules : allDiscountRules){
    if(member!=null){
      if(currentdiscountRules.getClubMemberID()==member.getMemberID()){
        discountRulesToReturn.add(currentdiscountRules);
      }
    }

    if(currentdiscountRules.getClubMemberID()==null){
      discountRulesToReturn.add(currentdiscountRules);
    }
    if(currentdiscountRules.getClubMemberID() == "0" && member!=null){
      discountRulesToReturn.add(currentdiscountRules);
    }
  }

  return discountRulesToReturn;
}
}