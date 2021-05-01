package se.kth.iv1350.danielhenning.dto;

import se.kth.iv1350.danielhenning.model.Sale;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The SaleDTO class represents the current sale as a 
 * Data Transfer Object. SaleDTOs are used to send 
 * information about the current sale between classes
 * without giving out the functionality of the class Sale
 */
public final class SaleDTO {

  private ArrayList<ItemRowDTO> itemRows;
  private boolean lastItemFound;
  private double runningTotal;
  private LocalDateTime dateTime;
  private int numberOfItems;
  private int numberOfRows;
  private double amountPaid;
  private double change;
  private double discountOnWholeSale;
  private ClubMemberDTO member;

  /**
   * Creates a new instance of the class SaleDTO
   * @param sale is the sale being converted to a DTO
   */
  public SaleDTO(Sale sale) {
    itemRows = new ArrayList<ItemRowDTO>();
    for(int i = 0; i < sale.getItemList().getItemRows().size(); i++) {
      this.itemRows.add(new ItemRowDTO(sale.getItemList().getItemRows().get(i)));
    }
    this.lastItemFound = sale.getLastItemFound();
    this.runningTotal = sale.getRunningTotal();
    this.dateTime = sale.getDateTime();
    this.numberOfItems = sale.getItemList().getNumberOfItems();
    this.numberOfRows = sale.getItemList().getNumberOfRows();
    this.amountPaid = sale.getAmountPaid();
    this.change = sale.getChange();
    this.discountOnWholeSale = sale.getDiscountOnWholeSale();
    this.member = new ClubMemberDTO(null,null);
  }

  public ArrayList<ItemRowDTO> getItemRows() {
    return itemRows;
  }

  public double getRunningTotal() {
    return runningTotal;
  }

  public int getNumberOfItems() {
    return numberOfItems;
  }

  public int getNumberOfRows() {
    return numberOfRows;
  }

  public boolean getLastItemFound() {
    return lastItemFound;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public double getAmountPaid() {
    return amountPaid;
  }

  public double getChange() {
    return change;
  }

  public double getDiscountOnWholeSale() {
    return discountOnWholeSale;
  }

  public void printSale() {
    /*if(lastItemFound == false) {
      System.out.println("--------------------------");
      System.out.println("|INVALID ITEM IDENTIFIER!|");
      System.out.println("--------------------------");
    }
    System.out.println("---------------------------------------------------------------");
    System.out.println("| Row | Description | ID  | Quantity  | Discount  | Row Price |");
    System.out.println("---------------------------------------------------------------");
    for(int i = 0; i < itemRows.size(); i++) {
      System.out.println("|  " + (i + 1) + "  |   " + itemRows.get(i).toString());
      System.out.println("---------------------------------------------------------------");
    }
    System.out.println("Discount on whole sale: " + discountOnWholeSale);
    System.out.println("Running Total: " + runningTotal + "             Number Of Items: " + numberOfItems);
    System.out.println("Amount Paid: " + amountPaid + "               Change: " + change);
    System.out.print("Date: " + dateTime.getYear() + "-" + dateTime.getMonth().getValue() + "-" + dateTime.getDayOfMonth());

    int minute = dateTime.getMinute();
    String min;

    if(minute < 10) {
      min = "0" + minute;
    } else{
      min = "" + minute;
    }

    System.out.println("                  Time: " + dateTime.getHour() + ":" + min + "\n");*/
  }
}
