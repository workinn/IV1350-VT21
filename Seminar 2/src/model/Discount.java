package model;

import DTO.ClubMemberDTO;
import DTO.SaleDTO;
import integration.DiscountHandler;
import integration.MemberHandler;

public class Discount {

    private DiscountHandler discountHandler;
    private MemberHandler memberHandler;
    
    public Discount(DiscountHandler discountHandler, MemberHandler memberHandler){
        this.discountHandler = discountHandler;
        this.memberHandler = memberHandler;
    }

    public SaleDTO addDiscount(SaleDTO saleDTO, String customerID){

        ClubMemberDTO clubMemberDTO = memberHandler.getMember(customerID);
        SaleDTO returnSaleDTO = discountHandler.addDiscount(saleDTO, clubMemberDTO);

        return returnSaleDTO;
    }
}
