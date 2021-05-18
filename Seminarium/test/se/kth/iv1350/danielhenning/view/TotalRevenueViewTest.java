package se.kth.iv1350.danielhenning.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalRevenueViewTest {

    private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private TotalRevenueView totalRevenueView;



    @BeforeEach
    public void startUp(){
        totalRevenueView = new TotalRevenueView();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown(){
        totalRevenueView=null;
    }

    @Test
    public void testTotalRevenueViewWith100Input(){
        totalRevenueView.updateTotalSaleAmount(100);
        assertEquals("Todays Sale: 100.0", outputStreamCaptor.toString().trim());

    }
    @Test
    public void testTotalRevenueViewWith500Input(){
        totalRevenueView.updateTotalSaleAmount(500);
        assertEquals("Todays Sale: 500.0", outputStreamCaptor.toString().trim());

    }

    
}
