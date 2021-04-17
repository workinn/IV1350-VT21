package model;
import DTO.SaleDTO;

public class Reciept {
  
  private String address;
  private String store;
  private String phoneNumber;
  private SaleDTO sale;

  public Reciept(SaleDTO sale){
      this.address = "Testvägen 5";
      this.store = "Conceptstore";
      this.phoneNumber = "0700000123";
      this.sale = sale;
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
