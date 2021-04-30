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
    private double totalDiscountToday;

    public Discount(DiscountHandler discountHandler, MemberHandler memberHandler){
        this.discountHandler = discountHandler;
        this.memberHandler = memberHandler;
        this.totalDiscountToday = 0;
    }

    public DiscountDTO discountCheck(String customerID, SaleDTO sale){

        ArrayList<ItemRowDTO> rowsWithDiscount = new ArrayList<ItemRowDTO>();
        ArrayList<DiscountRulesDTO> discountRules = new ArrayList<DiscountRulesDTO>();
        ItemRowDTO oldRow;
        ItemRowDTO newRow;
        ClubMemberDTO member = memberHandler.getMember(customerID);
        double amountDiscount=0;
        double totalSaleDiscount=0;
        double runningTotalAfterItemDiscount=0;

        for(int i = 0; i<sale.getItemRows().size();i++){
            amountDiscount=0;
            discountRules = discountHandler.getDiscountRules(sale.getItemRows().get(i).getitem().getItemIdentifier());

            if(discountRules.size()>0){
                amountDiscount= getDiscountAmount(discountRules, sale.getItemRows().get(i), member);   
            }
        oldRow = sale.getItemRows().get(i);
        newRow = new ItemRowDTO(oldRow,amountDiscount);
        rowsWithDiscount.add(newRow);
        }
        for(int i=0; i<rowsWithDiscount.size();i++){
          runningTotalAfterItemDiscount= runningTotalAfterItemDiscount+rowsWithDiscount.get(i).getPriceIncludingDiscount();
        }
        
        totalSaleDiscount = getDiscountTotalAmount(runningTotalAfterItemDiscount, member);

        return new DiscountDTO(rowsWithDiscount, member, totalSaleDiscount);

    }

    private double getDiscountAmount(ArrayList<DiscountRulesDTO> discountRules, ItemRowDTO item, ClubMemberDTO member){
        double amountToReturn=0;
        int multiplier = 0;
        for(int i=0; i<discountRules.size(); i++){
            multiplier = item.getQuantity()/discountRules.get(i).getQuantityToGetDiscount();

            if(multiplier>=1 && discountRules.get(i).getClubMemberID()==null && amountToReturn<(multiplier*discountRules.get(i).getDiscountAmount())){
               amountToReturn = multiplier*discountRules.get(i).getDiscountAmount();
            }
            if(multiplier>=1 && discountRules.get(i).getClubMemberID()=="0" && member.getMemberID() !=null && amountToReturn<(multiplier*discountRules.get(i).getDiscountAmount())){
                amountToReturn = multiplier*discountRules.get(i).getDiscountAmount();
            }

            if(multiplier>=1 && discountRules.get(i).getClubMemberID()== member.getMemberID() && amountToReturn<(multiplier*discountRules.get(i).getDiscountAmount())){
              amountToReturn = multiplier*discountRules.get(i).getDiscountAmount();
          }
          
        }
return amountToReturn;
    }

    private double getDiscountTotalAmount(double runningTotalAfterItemDiscount, ClubMemberDTO member){

      ArrayList<DiscountRulesTotalDTO> discountTotalRules = new ArrayList<DiscountRulesTotalDTO>();
      double amountToReturn=0;
      discountTotalRules = discountHandler.getDiscountTotalRules(runningTotalAfterItemDiscount);

      for(int i = 0; i<discountTotalRules.size();i++){
        if(discountTotalRules.get(i).getClubMemberID()==null && amountToReturn<discountTotalRules.get(i).getDiscountAmount())
        {
          amountToReturn= discountTotalRules.get(i).getDiscountAmount();
        }
        if(discountTotalRules.get(i).getClubMemberID()=="0" && member.getMemberID()!=null && amountToReturn<discountTotalRules.get(i).getDiscountAmount())
        {
          amountToReturn= discountTotalRules.get(i).getDiscountAmount();
        }
        if(discountTotalRules.get(i).getClubMemberID()==member.getMemberID() && amountToReturn<discountTotalRules.get(i).getDiscountAmount())
        {
          amountToReturn= discountTotalRules.get(i).getDiscountAmount();
        }
      }

      return amountToReturn;
    }
}




//public void discountCheck(SaleDTO saleDTO, String customerID){
    /** 
    ClubMemberDTO member = memberHandler.getMember(customerID);
    
    ArrayList<DiscountRulesDTO> discountRules;
    ArrayList<ItemRowDTO> itemRowsToSend = new ArrayList<ItemRowDTO>();
    ItemInformationDTO item;
    ItemRowDTO itemRow;
    double discountAmount = 0;

    for(int i = 0; i < saleDTO.getItemRows().size(); i++) {
        itemRow = saleDTO.getItemRows().get(i);
        itemID = itemRow.getitem().getItemIdentifier();

        discountRules = discountHandler.getDiscountRules(itemID);

        discountAmount = getDiscountAmount(itemrow, discountRules, member);
    
        for(int i=0; i<discountRulesRecived.size();i++){
            System.out.println(discountRulesRecived.get(i).getItemIdentifier);
        }
    }

}

private double getDiscountAmount(ItemRowDTO itemRow, ArrayList<DiscountRulesDTO> discountRules, ClubMemberDTO member) {
    double discount = 0;
    double amount;
    int multiplier;
    DiscountRulesDTO discountRule;
    for(int i = 0; i < discountRules.size(); i++) {
        discountRule = discountRules.get(i);
        if(eligibleForDiscount(discountRule, itemRow, member) && discountRule.getDiscount() > discount) {
            multiplier = itemRow.getQuantity() / discountRule.getQuantityToGetDiscount;
            discount = discountRule.getDiscount();
            amount = discountRule.getDiscount() * multiplier;
        }
    }



    return amount;
    */
//}

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