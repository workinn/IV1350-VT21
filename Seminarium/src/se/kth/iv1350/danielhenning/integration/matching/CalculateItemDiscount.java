package se.kth.iv1350.danielhenning.integration.matching;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.AllDiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
/**
 * Class that calculates the discount on all the indevidual items in a sale.
 */
public class CalculateItemDiscount implements CalculateDiscountStrategy{
    
    public CalculateItemDiscount(){

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
        String itemIdentifier;
        int itemQuantity;
        int discountQunatity;
        String discountIdentifier;
        double itemRowDiscountAmount;
        double discountTotalAmount;
        double discountAmount;

        for(int i = 0; i < sale.getItemRows().size(); i++){ 

            itemRowDiscountAmount=0;
            oldRow=sale.getItemRows().get(i);
            itemIdentifier = oldRow.getItem().getItemIdentifier();
            itemQuantity = oldRow.getQuantity();

            for(AllDiscountRulesDTO currentDiscountRules : rules){

                discountIdentifier = currentDiscountRules.getItemIdentifier();
                discountQunatity=currentDiscountRules.getQuantityToGetDiscount();
                discountAmount= currentDiscountRules.getDiscountAmount();

                if(itemIdentifier == discountIdentifier && itemQuantity>=discountQunatity){
                    discountTotalAmount = (itemQuantity/discountQunatity*discountAmount);
                    if(discountTotalAmount>itemRowDiscountAmount){
                        itemRowDiscountAmount=discountTotalAmount;
                    }
                }
            }
            newRow = new ItemRowDTO(oldRow,itemRowDiscountAmount);
            rowsWithDiscount.add(newRow);
          }
        return new DiscountDTO(rowsWithDiscount, 0);

    }
    
}
