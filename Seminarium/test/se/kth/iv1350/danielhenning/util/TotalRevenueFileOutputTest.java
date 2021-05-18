package se.kth.iv1350.danielhenning.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalRevenueFileOutputTest {

    private TotalRevenueFileOutput totalRevenueFileOutput;



    @BeforeEach
    public void startUp(){
        totalRevenueFileOutput = new TotalRevenueFileOutput();
    }

    @AfterEach
    public void tearDown(){
        totalRevenueFileOutput=null;
    }
    

    @Test
    public void testTotalRevenueFileOutputWithh100Input(){
        totalRevenueFileOutput.updateTotalSaleAmount(100);
        double actual = totalRevenueFileOutput.getTotalRevenue();

        assertEquals(100, actual,"Numbers dont match");
    }


    @Test
    public void testTotalRevenueFileOutputWithh0Input(){
        totalRevenueFileOutput.updateTotalSaleAmount(0);
        double actual = totalRevenueFileOutput.getTotalRevenue();

        assertEquals(0, actual,"Numbers dont match");
    }

    @Test
    public void testTotalRevenueFileOutputWithh100InputAndThen500Input(){
        totalRevenueFileOutput.updateTotalSaleAmount(100);
        totalRevenueFileOutput.updateTotalSaleAmount(500);
        double actual = totalRevenueFileOutput.getTotalRevenue();

        assertEquals(500, actual,"Numbers dont match");
    }

    
}
