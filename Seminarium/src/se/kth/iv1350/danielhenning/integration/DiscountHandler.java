package se.kth.iv1350.danielhenning.integration;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

/**
 * The DiscountHandler class represents the system
 * that applies discounts to sales depending on
 * the current discount rules
 */
public class DiscountHandler {

  /**
   * Creates a new instance of the class DiscountHandler
   */
  public DiscountHandler() {
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
}
