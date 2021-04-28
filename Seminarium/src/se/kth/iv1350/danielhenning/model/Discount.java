package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.ItemRowDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.DiscountHandler;
import se.kth.iv1350.danielhenning.integration.MemberHandler;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;


public class Discount {

    private DiscountHandler discountHandler;
    private MemberHandler memberHandler;
    private double totalDiscountToday;

    public Discount(DiscountHandler discountHandler, MemberHandler memberHandler){
        this.discountHandler = discountHandler;
        this.memberHandler = memberHandler;
        this.totalDiscountToday = 0;
    }

    private void updateTotalDiscountToday(DiscountDTO discountDTO) {
       ArrayList<ItemRowDTO> items = discountDTO.getItemRowDTO();

        for(int i = 0; i < items.size(); i++) {
            totalDiscountToday += items.get(i).getDiscount();
        }
        totalDiscountToday += discountDTO.getTotalSaleDiscount();
    }

    public DiscountDTO addDiscount(SaleDTO saleDTO, String customerID){

        ClubMemberDTO member = memberHandler.getMember(customerID);
        DiscountDTO discountDTO = discountHandler.addDiscount(saleDTO, member);
        updateTotalDiscountToday(discountDTO);

        return discountDTO;
    }

    public double getDiscount() {
        return totalDiscountToday;
    }
}
