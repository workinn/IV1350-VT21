package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.DiscountHandler;
import se.kth.iv1350.danielhenning.integration.MemberHandler;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.DiscountRulesTotalDTO;

import java.util.ArrayList;

public class Discount {

  private DiscountHandler discountHandler;
  private MemberHandler memberHandler;

  public Discount(DiscountHandler discountHandler, MemberHandler memberHandler){
      this.discountHandler = discountHandler;
      this.memberHandler = memberHandler;
  }

  /**
   * This funktion checks if the costumer is eligable for discount and how much.
   * @param customerID a String with the costumer ID number.
   * @param sale a SaleDTO of the current sale.
   * @return a DiscountDTO with a ItemRowDTO with added discount aswell as ClubMemberDTO and totalSaleDiscount.
   */
  public DiscountDTO discountCheck(String customerID, SaleDTO sale){

    ArrayList<ItemRowDTO> rowsWithDiscount = new ArrayList<ItemRowDTO>();
    ArrayList<DiscountRulesDTO> discountRules = new ArrayList<DiscountRulesDTO>();
    ItemRowDTO oldRow;
    ItemRowDTO newRow;
    ClubMemberDTO member = memberHandler.getMember(customerID);
    double amountDiscount=0;
    double totalSaleDiscount=0;
    double runningTotalAfterItemDiscount=0;

    for(int i = 0; i < sale.getItemRows().size(); i++){
      amountDiscount=0;
      discountRules = discountHandler.getDiscountRules(sale.getItemRows().get(i), member);

      if(!discountRules.isEmpty()) {
          amountDiscount = getDiscountAmount(discountRules, sale.getItemRows().get(i), member);   
      }
      oldRow = sale.getItemRows().get(i);
      newRow = new ItemRowDTO(oldRow,amountDiscount);
      rowsWithDiscount.add(newRow);
    }
    for(int i = 0; i < rowsWithDiscount.size(); i++){
      runningTotalAfterItemDiscount = runningTotalAfterItemDiscount + rowsWithDiscount.get(i).getPriceIncludingDiscount();
    }
    
    totalSaleDiscount = getDiscountTotalAmount(runningTotalAfterItemDiscount, member);

    return new DiscountDTO(rowsWithDiscount, member, totalSaleDiscount);
  }

  private double getDiscountAmount(ArrayList<DiscountRulesDTO> discountRules, ItemRowDTO item, ClubMemberDTO member){
      double amountToReturn=0;
      int multiplier = 0;
      for(int i = 0; i < discountRules.size(); i++){
          multiplier = item.getQuantity()/discountRules.get(i).getQuantityToGetDiscount();

          if(multiplier*discountRules.get(i).getDiscountAmount()>amountToReturn){
            amountToReturn = multiplier*discountRules.get(i).getDiscountAmount();
          }
      }
    return amountToReturn;
  }

  private double getDiscountTotalAmount(double runningTotalAfterItemDiscount, ClubMemberDTO member){

    ArrayList<DiscountRulesTotalDTO> discountTotalRules = new ArrayList<DiscountRulesTotalDTO>();
    double amountToReturn=0;
    discountTotalRules = discountHandler.getDiscountTotalRules(runningTotalAfterItemDiscount, member);

    for(int i = 0; i<discountTotalRules.size();i++){
      if(amountToReturn<discountTotalRules.get(i).getDiscountAmount())
      {
        amountToReturn= discountTotalRules.get(i).getDiscountAmount();
      }
    }

    return amountToReturn;
  }
}