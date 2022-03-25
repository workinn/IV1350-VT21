package se.kth.iv1350.danielhenning.integration.matching;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.danielhenning.dto.AllDiscountRulesDTO;
import se.kth.iv1350.danielhenning.dto.ClubMemberDTO;
import se.kth.iv1350.danielhenning.dto.DiscountDTO;
import se.kth.iv1350.danielhenning.integration.CouldNotConnectToServerException;
import se.kth.iv1350.danielhenning.integration.HandlerCreator;
import se.kth.iv1350.danielhenning.integration.InventoryHandler;
import se.kth.iv1350.danielhenning.integration.ItemDoesNotExistException;
import se.kth.iv1350.danielhenning.model.Sale;
import se.kth.iv1350.danielhenning.model.SaleLog;

public class CalculateTotalAmountDiscountTest {
    
    private CalculateTotalAmountDiscount calculator;
    private HandlerCreator handler;
    private Sale sale;
    private SaleLog log;
    private ClubMemberDTO member;


    @BeforeEach
    public void startUp(){
        calculator = new CalculateTotalAmountDiscount();
        handler = new HandlerCreator();
        log = new SaleLog(handler.getAccountingHandler(), InventoryHandler.getInventoryHandler());
        sale = new Sale(handler, log);
    }

    @AfterEach
    public void tearDown(){
        calculator=null;
        handler=null;
    }



    @Test
    public void testDiscountTotalAmountCalculatorWith10ColaWithNullMember() throws CouldNotConnectToServerException, ItemDoesNotExistException{
        sale.addItem("1");
        sale.addQuantity(10);
        member = handler.getMemberHandler().getMember(null);    
        ArrayList<AllDiscountRulesDTO> rules = new ArrayList<>();
        rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(5,actual.getTotalSaleDiscount(),"The discount is incorrect");

    }

    @Test
    public void testDiscountTotalAmountCalculatorWith10ColaWith1337Member() throws CouldNotConnectToServerException, ItemDoesNotExistException{
        sale.addItem("1");
        sale.addQuantity(10);
        member = handler.getMemberHandler().getMember("1337");    
        ArrayList<AllDiscountRulesDTO> rules = new ArrayList<>();
        rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(20,actual.getTotalSaleDiscount(),"The discount is incorrect");

    }
    @Test
    public void testDiscountTotalAmountCalculatorWith1ColaWith1337Member() throws CouldNotConnectToServerException, ItemDoesNotExistException{
        sale.addItem("1");
        member = handler.getMemberHandler().getMember("1337");    
        ArrayList<AllDiscountRulesDTO> rules = new ArrayList<>();
        rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(0,actual.getTotalSaleDiscount(),"The discount is incorrect");

    }


    @Test
    public void testDiscountTotalAmountCalculatorWith1ColaWith1Member() throws CouldNotConnectToServerException, ItemDoesNotExistException{
        sale.addItem("1");
        sale.addQuantity(10);
        member = handler.getMemberHandler().getMember("1");    
        ArrayList<AllDiscountRulesDTO> rules = new ArrayList<>();
        rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(10,actual.getTotalSaleDiscount(),"The discount is incorrect");

    }

}
