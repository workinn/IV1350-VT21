package se.kth.iv1350.danielhenning.dto;

import java.util.ArrayList;

/**
 * The class DiscountDTO is a Data Transfer Object to relay 
 * what discounts where added to what row of item
 */
public final class DiscountDTO {
  
    private ArrayList<ItemRowDTO> itemRows;
    private ClubMemberDTO member;
    private double totalSaleDiscount;

    /**
     * Creates a new instance of the class DiscountDTO
     * @param itemRows is a list of all rows of items in a sale. Each row have eligible discounts added to it
     * @param member is the Club Member used to look for discounts
     * @param totalSaleDiscount is the total amount of discounts given to this sale
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
