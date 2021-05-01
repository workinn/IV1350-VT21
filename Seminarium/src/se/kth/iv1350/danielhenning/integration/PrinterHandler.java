package se.kth.iv1350.danielhenning.integration;

import se.kth.iv1350.danielhenning.dto.ReceiptDTO;

/**
 * The PrinterHandler class represents the interface 
 * between the System Under Development and the
 * receipt printer
 */
public class PrinterHandler {

  /**
   * Creates a new instance of the class PrinterHandler
   */
  public PrinterHandler() {
  }

  /**
   * The method printReceipt prints the given receipt
   * @param receipt contains all information about the sale to be printed
   */
  public void printReceipt(ReceiptDTO receipt){
    System.out.print(receipt.getReceiptToPrint());
  }
}
