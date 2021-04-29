package se.kth.iv1350.danielhenning.dto;

import java.util.ArrayList;

public class DiscountDTO {
  
    private ArrayList<ItemRowDTO> itemRows;
    private ClubMemberDTO member;
    private double totalSaleDiscount;
/**
 * Create a DiscountDTO to calculate discount in sale.
 * @param itemRows is an array of ItemRowDTO that has been added discount on.
 * @param member the ClubMebmerDTO is sent in the Discount to be able to connect the Costumer to the sale.
 * @param totalSaleDiscount is a number of the total amount of discount given, is used for statistic purpouses.
 */
    public DiscountDTO(ArrayList<ItemRowDTO> itemRows, ClubMemberDTO member, double totalSaleDiscount) {
      this.itemRows = itemRows;
      this.member = member;
      this.totalSaleDiscount = totalSaleDiscount;
    }
      
    public ArrayList<ItemRowDTO> getItemRowDTO(){
      return this.itemRows;
    }

    public double getTotalSaleDiscount() {
      return totalSaleDiscount;
    }

    public ClubMemberDTO getMember() {
      return member;
    }
}
