package se.kth.iv1350.danielhenning.dto;

public class DiscountRulesTotalDTO {
    private double amountTotalToGetDiscount;
    private double discountAmount;
    private String clubMemberID;


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
