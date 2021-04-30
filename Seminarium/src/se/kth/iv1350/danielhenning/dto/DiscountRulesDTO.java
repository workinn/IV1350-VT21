package se.kth.iv1350.danielhenning.dto;

import java.lang.String;

public class DiscountRulesDTO {
    private String itemIdentifier;
    private int quantityToGetDiscount;
    private double discountAmount;
    private String clubMemberID;

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
