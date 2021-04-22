package src.model;

import src.DTO.SaleDTO;

public class CashRegister {

  private float balance;

    public CashRegister(float initialBalance) {
    this.balance = initialBalance;
    }

    public void addPayment(SaleDTO sale) {
      balance += sale.getRunningTotal();
    }

    public float getChange(int amountPaid, SaleDTO sale) {
      return amountPaid - sale.getRunningTotal();
    }

    public float getBalance() {
      return balance;
    }
}
