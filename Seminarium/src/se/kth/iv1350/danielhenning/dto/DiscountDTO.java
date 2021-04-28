package se.kth.iv1350.danielhenning.dto;

import java.util.ArrayList;

public class DiscountDTO {
  
    private ArrayList<ItemRowDTO> itemRows;
    private ClubMemberDTO member;
    private double totalSaleDiscount;

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
