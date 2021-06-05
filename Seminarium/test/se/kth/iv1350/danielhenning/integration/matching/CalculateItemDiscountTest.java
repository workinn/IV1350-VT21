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

public class CalculateItemDiscountTest {
    private CalculateItemDiscount calculator;
    private HandlerCreator handler;
    private Sale sale;
    private SaleLog log;
    private ClubMemberDTO member;


    @BeforeEach
    public void startUp(){
        calculator = new CalculateItemDiscount();
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
    public void testDiscountItemCalculato111rWith10ColaWithNullMember() throws CouldNotConnectToServerException, ItemDoesNotExistException{
        sale.addItem("1");
        sale.addQuantity(10);
        member = handler.getMemberHandler().getMember(null);    
        ArrayList<AllDiscountRulesDTO> rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(100,actual.getItemRowDTO().get(0).getDiscount(),"The discount is incorrect");

    }


    @Test
    public void testDiscountItemCalculatorWith10ColaWith1337Member() throws CouldNotConnectToServerException, ItemDoesNotExistException{
        sale.addItem("1");
        sale.addQuantity(10);
        member = handler.getMemberHandler().getMember("1337");    
        ArrayList<AllDiscountRulesDTO> rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(100,actual.getItemRowDTO().get(0).getDiscount(),"The discount is incorrect");

    }

    @Test
    public void testDiscountItemCalculatorWith1ColaWith1337Member() throws CouldNotConnectToServerException, ItemDoesNotExistException{
        sale.addItem("1");
        member = handler.getMemberHandler().getMember("1337");    
        ArrayList<AllDiscountRulesDTO> rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(0,actual.getItemRowDTO().get(0).getDiscount(),"The discount is incorrect");

    }

    @Test
    public void testDiscountItemCalculatorWithNoExistingItem(){
        try {
            sale.addItem("100");
        } catch (Exception e) {
            //TODO: handle exception
        }
        
        member = handler.getMemberHandler().getMember("1337");    
        ArrayList<AllDiscountRulesDTO> rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(0,actual.getItemRowDTO().size(),"The is a row in discountDTo that is not supposed to be there");

    }

    @Test
    public void testDiscountItemCalculatorWith10ColaAnd10AppleMember1337() throws CouldNotConnectToServerException, ItemDoesNotExistException{
        sale.addItem("1");
        sale.addQuantity(10);
        sale.addItem("1337");
        sale.addQuantity(10);
        member = handler.getMemberHandler().getMember("1337");    
        ArrayList<AllDiscountRulesDTO> rules = handler.getDiscountHandler().getAllDiscountRules(member);
        DiscountDTO actual = calculator.getDiscount(rules, sale.endSale());

        assertEquals(100.0,actual.getItemRowDTO().get(0).getDiscount(),"The discount is incorrect");
        assertEquals(40.0,actual.getItemRowDTO().get(1).getDiscount(),"The discount is incorrect");

    }

}


