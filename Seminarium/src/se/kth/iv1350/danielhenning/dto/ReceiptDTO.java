package se.kth.iv1350.danielhenning.dto;

/**
 * The class ReceiptDTO represents a 
 * Data Transfer Object of a receipt
 */
public final class ReceiptDTO {
  
  private String address;
  private String store;
  private String phoneNumber;
  private SaleDTO sale;

  /**
   * Creates a new instance of the class ReceiptDTO
   * @param sale is the sale being added to the receipt for later printing
   */
  public ReceiptDTO(SaleDTO sale) {
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
