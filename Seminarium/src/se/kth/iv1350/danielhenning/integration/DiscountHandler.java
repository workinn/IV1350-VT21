package se.kth.iv1350.danielhenning.integration;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesTotalDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

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
  
  public ArrayList<DiscountRulesDTO> getDiscountRules(String itemID){
    ArrayList<DiscountRulesDTO> discountRulesToSend = new ArrayList<DiscountRulesDTO>();
    for(int i = 0; i < discountRulesArray.size(); i++) {
      if(itemID == discountRulesArray.get(i).getItemIdentifier()) {
        discountRulesToSend.add(discountRulesArray.get(i));
      }
    }
   return discountRulesToSend;
  }

  public ArrayList<DiscountRulesTotalDTO> getDiscountTotalRules(double runningTotal){
    ArrayList<DiscountRulesTotalDTO> discountRulesTotalToSend = new ArrayList<DiscountRulesTotalDTO>();

    for(int i =0 ; i<discountRulesTotalArray.size();i++){
      if(runningTotal>discountRulesTotalArray.get(i).getAmountTotalToGetDiscount()){
        discountRulesTotalToSend.add(discountRulesTotalArray.get(i));
      }
    }
    return discountRulesTotalToSend;

}
}

  /**
   * The method addDiscount adds discounts to the given sale (saleDTO) 
   * depending on discount rules.
   * Right now it has hard coded discount rules but may contact an 
   * External Discount System in the future
   * @param saleDTO is the sale to give discounts to
   * @param member is the Club Member asking for discounts. Could be null if customer is not a Club Member
   * @return a DiscountDTO with all eligible discounts added to it
   */
  /*
  public DiscountDTO addDiscount(SaleDTO saleDTO, ClubMemberDTO member) {

    ArrayList<ItemRowDTO> rowsWithDiscount = new ArrayList<ItemRowDTO>();
    ItemRowDTO oldRow;
    ItemRowDTO newRow;
    double runningTotal = 0;
    double totalSaleDiscount = 0;
    int multiplier;
    double discount;

    for(int i = 0; i < saleDTO.getItemRows().size(); i++) {
      oldRow = saleDTO.getItemRows().get(i);
      if(oldRow.getitem().getItemIdentifier() == "1" && oldRow.getQuantity() >= 5) {
        multiplier = oldRow.getQuantity() / 5;
        discount = oldRow.getitem().getPrice() * 2 * multiplier;
        newRow = new ItemRowDTO(oldRow, discount);
        rowsWithDiscount.add(newRow);
        runningTotal += newRow.getPriceIncludingDiscount();
      } else if (oldRow.getitem().getItemIdentifier() == "1337" && oldRow.getQuantity() >= 10) {
        multiplier = oldRow.getQuantity() / 10;
        discount = oldRow.getitem().getPrice() * 2 * multiplier;
        newRow = new ItemRowDTO(oldRow, discount);
        rowsWithDiscount.add(newRow);
        runningTotal += newRow.getPriceIncludingDiscount();
      }
      else {
        rowsWithDiscount.add(oldRow);
        runningTotal += oldRow.getPriceIncludingDiscount();
      }
    }
    if(member != null & runningTotal>= 100) { 
      totalSaleDiscount = 10.0;
    }
     return new DiscountDTO(rowsWithDiscount, member, totalSaleDiscount);
  }
  */