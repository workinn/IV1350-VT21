package se.kth.iv1350.danielhenning.integration;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

public class DiscountHandler {

  public DiscountHandler() {

  }

  public DiscountDTO addDiscount(SaleDTO saleDTO, ClubMemberDTO member) {
    /* 
    Contacts External Discount System to get a SaleDTO with discount added
    */

    ArrayList<ItemRowDTO> rowsWithDiscount = new ArrayList<ItemRowDTO>();
    ItemRowDTO oldRow;
    ItemRowDTO newRow;
    double runningTotal = 0;
    double totalSaleDiscount = 0;

    for(int i = 0; i < saleDTO.getItemRows().size(); i++) {
      oldRow = saleDTO.getItemRows().get(i);
      if(oldRow.getitem().getItemIdentifier() == "1" && oldRow.getQuantity() >= 5) {
        newRow = new ItemRowDTO(oldRow, oldRow.getPriceIncludingDiscount() * 0.40);
        System.out.println("Discount found - 5 Coca-Cola for the price of 3!");
        rowsWithDiscount.add(newRow);
        runningTotal += newRow.getPriceIncludingDiscount();
      } else {
        rowsWithDiscount.add(oldRow);
        runningTotal += oldRow.getPriceIncludingDiscount();
      }
    }
    if(member != null & runningTotal>= 100) { 
      System.out.println("Discount found - 10 SEK off on total sale!");
      totalSaleDiscount = 10.0;
    }
     return new DiscountDTO(rowsWithDiscount, member, totalSaleDiscount);
  }
}
