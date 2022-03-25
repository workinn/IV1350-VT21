package se.kth.iv1350.danielhenning.model;
/**
 * Interface for reciveing notifications about the total amount sold today.
 */
public interface TotalRevenueObserver {
    void updateTotalSaleAmount(double amount);
}
