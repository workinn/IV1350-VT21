package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.AllDiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
/**
 * Class that calculates discount on the total amount on a sale.
 */
public class CalculateTotalAmountDiscount implements CalculateDiscountStrategy{
    private double totalSaleDiscount;
    private double amountToCompare;
    CalculateTotalAmountDiscount(){
    }
    @Override
    /**
     * Calculates the discount
     * @param rules the discountrules
     * @param sale the current saleDTO
     * @return is discountDTO
     */
    public DiscountDTO getDiscount(ArrayList<AllDiscountRulesDTO> rules, SaleDTO sale) {
        totalSaleDiscount=0;

        for(AllDiscountRulesDTO currentRule : rules){
            if(currentRule.getType()=="total" && currentRule.getAmountToGetDiscount()<sale.getRunningTotal()){
                amountToCompare=currentRule.getDiscountAmount();
                if(amountToCompare>totalSaleDiscount){
                    totalSaleDiscount=amountToCompare;
                }
            }
        }
        DiscountDTO discountDTOToReturn;
        return discountDTOToReturn = new DiscountDTO(sale.getItemRows(), totalSaleDiscount);
    }
    
}
