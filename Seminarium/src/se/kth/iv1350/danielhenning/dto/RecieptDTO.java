package se.kth.iv1350.danielhenning.dto;

public final class RecieptDTO {
  
  private String address;
  private String store;
  private String phoneNumber;
  private SaleDTO sale;
  double amountPaid;
  double change;

  public RecieptDTO(SaleDTO sale, double amountPaid, double change) {
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
