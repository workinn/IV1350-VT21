package se.kth.iv1350.danielhenning.model;
import se.kth.iv1350.danielhenning.dto.SaleDTO;

public class Reciept {
  
  private String address;
  private String store;
  private String phoneNumber;
  private SaleDTO sale;
  float amountPaid;
  float change;

  public Reciept(SaleDTO sale, float amountPaid, float change) {
      this.address = "Testvägen 5";
      this.store = "Conceptstore";
      this.phoneNumber = "0700000123";
      this.sale = sale;
      this.amountPaid = amountPaid;
      this.change = change;
  }

  public String getAddress() {
    return address;
  }

  public String getStore() {
    return store;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public SaleDTO getSale() {
    return sale;
  }
}
