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
  private StringBuilder receiptToPrint;
  /**
   * Creates a new instance of the class ReceiptDTO
   * @param sale is the sale being added to the receipt for later printing
   */
  public ReceiptDTO(SaleDTO sale) {
    this.receiptToPrint = new StringBuilder();
    this.address = "Testvägen 5";
    this.store = "Conceptstore";
    this.phoneNumber = "070 - 000 01 23";
    this.sale = sale;

    receiptToPrint.append("------------------------------------------------------------\n");
    receiptToPrint.append("|                       " + store + "                       |\n");
    receiptToPrint.append("|                        " + address + "                       |\n");
    receiptToPrint.append("|                      " + phoneNumber + "                     |\n");
    receiptToPrint.append("|   Date: " + sale.getDateTime().getYear()+"/");
    receiptToPrint.append(sale.getDateTime().getMonthValue()+"/");
    receiptToPrint.append(sale.getDateTime().getDayOfMonth()+"\t");
    receiptToPrint.append("                     Time: " + sale.getDateTime().getHour()+":");
    if(sale.getDateTime().getMinute() <= 9) {
      receiptToPrint.append("0" + sale.getDateTime().getMinute());
    } else {
      receiptToPrint.append(sale.getDateTime().getMinute());
    }
    receiptToPrint.append("   |\n");
    receiptToPrint.append("------------------------------------------------------------\n");
    receiptToPrint.append("| Description | ItemID | Qunatity | Discount |  TotalPrice |\n");
    receiptToPrint.append("------------------------------------------------------------\n");

    for(int i = 0; i<sale.getItemRows().size();i++){
      receiptToPrint.append("|     " + sale.getItemRows().get(i).toString() + "   ");
      receiptToPrint.append("\n");
      receiptToPrint.append("------------------------------------------------------------\n");
    }
    receiptToPrint.append("| Total Sale Discount " + sale.getDiscountOnWholeSale() + "                                 |\n");
    receiptToPrint.append("| Running Total: "+ sale.getRunningTotal() + "                                     |\n");
    receiptToPrint.append("| Amount Paid "+sale.getAmountPaid() + "                                        |\n");
    receiptToPrint.append("| Change: "+sale.getChange() + "                                            |\n");
    receiptToPrint.append("------------------------------------------------------------\n");
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

  public StringBuilder getReceiptToPrint(){
    return this.receiptToPrint;
  }

 // public StringBuilder getReceiptToPrint(){
 //   return this.receiptToPrint;
 // }
}
