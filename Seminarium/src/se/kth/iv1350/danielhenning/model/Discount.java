package se.kth.iv1350.danielhenning.model;

import java.util.ArrayList;

import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.ItemInformationDTO;
import se.kth.iv1350.danielhenning.dto.SaleDTO;
import se.kth.iv1350.danielhenning.integration.DiscountHandler;
import se.kth.iv1350.danielhenning.integration.MemberHandler;

public class Discount {

    private DiscountHandler discountHandler;
    private MemberHandler memberHandler;
    private float totalDiscount;
    
    public Discount(DiscountHandler discountHandler, MemberHandler memberHandler){
        this.discountHandler = discountHandler;
        this.memberHandler = memberHandler;
        this.totalDiscount = 0;
    }

    public SaleDTO addDiscount(SaleDTO saleDTO, String customerID){

        ClubMemberDTO member = memberHandler.getMember(customerID);

        ArrayList<ItemInformationDTO> oldItems = saleDTO.getItems();
        ArrayList<ItemInformationDTO> newItems = new ArrayList<ItemInformationDTO>();
        
        float runningTotal = 0;

        // Go through every item from SaleDTO and add discounts
        for(int i = 0; i < oldItems.size(); i++) {
            newItems.add(discountHandler.addDiscount(oldItems.get(i), member));
            totalDiscount += newItems.get(i).getDiscount();
            runningTotal += (newItems.get(i).getPrice() * newItems.get(i).getQuantity());
        }

        SaleDTO returnSaleDTO = new SaleDTO(newItems, runningTotal - totalDiscount, saleDTO.getLastItemFound());

        return returnSaleDTO;
    }

    public float getDiscount() {
        return totalDiscount;
    }
}
