package se.kth.iv1350.danielhenning.model;

import java.util.concurrent.RejectedExecutionException;

import se.kth.iv1350.danielhenning.dto.SaleDTO;

/**
 * The CashRegister class represents the point of
 * sale's cash register
 */
public class CashRegister {

  private double balance;

    /**
     * Creates a new instance of the class CashRegister
     * @param initialBalance is the initial cash balance in the cash register at startup
     */
    public CashRegister(double initialBalance) {
    this.balance = initialBalance;
    }

    /**
     * The method addPayment adds the amount of the current 
     * sale to the cash registers balance
     * @param sale contains the amount of the current sale to be added to the cash registers balance
     */
    public void addPayment(SaleDTO saleDTO) {
      balance += saleDTO.getRunningTotal();
    }

    /**
     * The method getChange calculate what change to give back given
     * the amount paid and the current sale
     * @param amountPaid is the amount paid
     * @param sale is the current sale
     * @return the change
     */
    public double getChange(double amountPaid, SaleDTO saleDTO) {
      return amountPaid - saleDTO.getRunningTotal();
    }

    public double getBalance() {
      return balance;
    }
}
