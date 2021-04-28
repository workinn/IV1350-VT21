package se.kth.iv1350.danielhenning.model;

import se.kth.iv1350.danielhenning.dto.SaleDTO;

public class CashRegister {

  private double balance;

    public CashRegister(double initialBalance) {
    this.balance = initialBalance;
    }

    public void addPayment(SaleDTO sale) {
      balance += sale.getRunningTotal();
    }

    public double getChange(int amountPaid, SaleDTO sale) {
      return amountPaid - sale.getRunningTotal();
    }

    public double getBalance() {
      return balance;
    }
}
