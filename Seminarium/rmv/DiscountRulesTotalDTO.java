package se.kth.iv1350.danielhenning.dto;

/**
 * The class DiscountRulesTotalDTO is a Data Transfer Object to relay 
 * what requirements that must be met to get a discount on a total
 * sale. The parameters are what amount you need to buy goods for to get
 * the total discount, how much the total discount is and if you need
 * to be a club member or not. You may need to be a specific club member
 * to get a personalized discount
 */
public class DiscountRulesTotalDTO {
    private double amountTotalToGetDiscount;
    private double discountAmount;
    private String clubMemberID;

/**
 * Creastes a instance of DiscountRulesDTO that contains information to calculate total discount on a sale.
 * @param amountTotalToGetDiscount a double representing the amount needed to get the discount.
 * @param discountTotalAmount a double representing the amount of dicount.
 * @param clubMemberID a String representing the clubmember connected to the discount, 0 = every clubmember, null represents every costumer.
 */
    public DiscountRulesTotalDTO(double amountTotalToGetDiscount,double discountTotalAmount, String clubMemberID){
        this.amountTotalToGetDiscount= amountTotalToGetDiscount;
        this.discountAmount = discountTotalAmount;
        this.clubMemberID = clubMemberID;
    }

    public double getAmountTotalToGetDiscount(){
        return this.amountTotalToGetDiscount;
    }
    
    public double getDiscountAmount(){
        return this.discountAmount;
    }
    public String getClubMemberID(){
        return this.clubMemberID;
    }
}
