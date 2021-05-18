package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.AllDiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
/**
 * Class that calculates the discount on all the indevidual items in a sale.
 */
public class CalculateItemDiscount implements CalculateDiscountStrategy{
    CalculateItemDiscount(){

    }

    @Override
        /**
     * Calculates the discount
     * @param rules the discountrules
     * @param sale the current saleDTO
     * @return is discountDTO
     */
    public DiscountDTO getDiscount(ArrayList<AllDiscountRulesDTO> rules, SaleDTO sale) {
        ArrayList<ItemRowDTO> rowsWithDiscount = new ArrayList<ItemRowDTO>();
        ItemRowDTO oldRow;
        ItemRowDTO newRow;
        DiscountDTO discountDTOToReturn;
        double amountDiscount;
        double compareAmount;

        for(int i = 0; i < sale.getItemRows().size(); i++){ 
            amountDiscount=0;
            oldRow=sale.getItemRows().get(i);
            for(AllDiscountRulesDTO currentDiscountRules : rules){
                System.out.println(currentDiscountRules.getType());
                if(oldRow.getItem().getItemIdentifier() == currentDiscountRules.getItemIdentifier() && oldRow.getQuantity()>currentDiscountRules.getQuantityToGetDiscount()){
                    compareAmount = (oldRow.getQuantity()/currentDiscountRules.getQuantityToGetDiscount())*currentDiscountRules.getDiscountAmount();
                    if(compareAmount>amountDiscount){
                        amountDiscount=compareAmount;
                    }
                }
            }
            newRow = new ItemRowDTO(oldRow,amountDiscount);
            rowsWithDiscount.add(newRow);
          }
        return discountDTOToReturn = new DiscountDTO(rowsWithDiscount, 0);

    }
    
}
