package se.kth.iv1350.danielhenning.view;

import se.kth.iv1350.danielhenning.model.TotalRevenueObserver;
/**
 * Prints the total amount sold today to the console.
 */
public class TotalRevenueView implements TotalRevenueObserver{
    private double totalRevenue;
    /**
     * Creates the class that stors the amount sold today and writes to the console.
     */
    public TotalRevenueView(){
        this.totalRevenue = 0;
    }
    /**
     * updates the total amount sold.
     */
    @Override
    public void updateTotalSaleAmount(double amount){
        this.totalRevenue = amount;
        printCurrentRevenue();

    }
    /**
     * Prints to console.
     */
    private void printCurrentRevenue(){
        System.out.println("Todays Sale: "+totalRevenue+"\n");
    }
}
