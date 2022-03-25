package se.kth.iv1350.danielhenning.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.iv1350.danielhenning.model.TotalRevenueObserver;
/**
 * Prints the total amount sold today to a file.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private double totalRevenue;
    private PrintWriter revenueLog;
    /**
    * Constructs the class that writes to file.
    */
    public TotalRevenueFileOutput(){
        this.totalRevenue = 0;
        try {
            revenueLog = new PrintWriter(new FileWriter("TotalRevenue.txt"), true);
        } catch (IOException ex) {
            System.out.println("Could not create logfile.");
            ex.printStackTrace();
        }
    }
    /**
     * Updates the internal amount and writes to file twith the current otal amount sold today.
     */
    @Override
    public void updateTotalSaleAmount(double amount) {
        totalRevenue = amount;
        revenueLog.println(amount);      
    }

    public double getTotalRevenue(){
        return this.totalRevenue;
    }
}
