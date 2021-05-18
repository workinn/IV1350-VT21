package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.AllDiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
/**
 * Strategy pattern for calculate discount.
 */
public interface CalculateDiscountStrategy {
    /**
     * Calculates the discount
     * @param rules the discountrules
     * @param sale the current saleDTO
     * @return is discountDTO
     */
    DiscountDTO getDiscount(ArrayList<AllDiscountRulesDTO> rules, SaleDTO sale);
    
}
