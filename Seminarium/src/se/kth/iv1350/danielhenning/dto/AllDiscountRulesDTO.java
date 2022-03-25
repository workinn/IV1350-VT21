package se.kth.iv1350.danielhenning.dto;
import java.lang.String;


public class AllDiscountRulesDTO {
    private String type;
    private String itemIdentifier;
    private int quantityToGetDiscount;
    private double amountToGetDiscount;
    private double discountAmount;
    private String clubMemberID;
    

    public AllDiscountRulesDTO(String type,String itemIdentifier, int quantityToGetDiscount, double amountToGetDiscount,double discountAmount, String clubMemberID){
        this.itemIdentifier = itemIdentifier;
        this.quantityToGetDiscount= quantityToGetDiscount;
        this.discountAmount = discountAmount;
        this.clubMemberID = clubMemberID;
        this.type = type;
        this.amountToGetDiscount = amountToGetDiscount;
    }

    public String getType(){
        return this.type;
    }
    public String getItemIdentifier(){
        return itemIdentifier;
    }

    public int getQuantityToGetDiscount(){

        return quantityToGetDiscount;

    }
    public double getAmountToGetDiscount(){
        return this.amountToGetDiscount;
    }

    public double getDiscountAmount(){
        return discountAmount;
    }

    public String getClubMemberID(){
        return clubMemberID;
    }    
}
