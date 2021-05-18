package se.kth.iv1350.danielhenning.dto;

import java.lang.String;

/**
 * The class DiscountRulesDTO is a Data Transfer Object to relay 
 * a single discount rule. It's specified by what item the rule
 * applies for, how many of the item you need to buy to be eligible
 * for the discount, what amount the discount is for and if you 
 * need to be a member to get the discount. You may also need to 
 * be a specific member to get a personalized discount
 */
public class DiscountRulesDTO {
    private String itemIdentifier;
    private int quantityToGetDiscount;
    private double discountAmount;
    private String clubMemberID;
    
/**
 * Creates a new instance of DiscountRulesDTO that contains the variables to calculate discount on items.
 * @param itemIdentifier a String representing the item identifier.
 * @param quantityToGetDiscount an int representing the number of items needed to be purchased to get discount.
 * @param discountAmount a double with the dicount given.
 * @param clubMemberID a String representing the id connected to the discount, 0 = every clubmember, null represents every costumer.
 */
    public DiscountRulesDTO(String itemIdentifier, int quantityToGetDiscount,double discountAmount, String clubMemberID){
        this.itemIdentifier = itemIdentifier;
        this.quantityToGetDiscount= quantityToGetDiscount;
        this.discountAmount = discountAmount;
        this.clubMemberID = clubMemberID;
    }

    public String getItemIdentifier(){
        return itemIdentifier;
    }

    public int getQuantityToGetDiscount(){

        return quantityToGetDiscount;

    }

    public double getDiscountAmount(){
        return discountAmount;
    }

    public String getClubMemberID(){
        return clubMemberID;
    }
}
