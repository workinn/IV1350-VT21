package se.kth.iv1350.danielhenning.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.kth.iv1350.danielhenning.model.Sale;

public final class SaleDTO {

  private ArrayList<ItemRowDTO> itemRows;
  private boolean lastItemFound;
  private double runningTotal;
  private LocalDateTime dateTime;
  private int numberOfItems;
  private int numberOfRows;
  private double amountPaid;
  private double change;

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

  public void printSale() {
    System.out.println("---------------------------------------------------------------");
    System.out.println("| Row | Description | ID  | Quantity  | Discount  | Row Price |");
    System.out.println("---------------------------------------------------------------");
    for(int i = 0; i < itemRows.size(); i++) {
      System.out.println("|  " + (i + 1) + "  |   " + itemRows.get(i).toString());
      System.out.println("---------------------------------------------------------------");
    }
    System.out.println("Running Total: " + runningTotal + "            Number Of Items: " + numberOfItems);
    System.out.println("Amount Paid: " + amountPaid + "               Change: " + change);
    System.out.print("Date: " + dateTime.getYear() + "-" + dateTime.getMonth().getValue() + "-" + dateTime.getDayOfMonth());
    System.out.println("                Time: " + dateTime.getHour() + ":" + dateTime.getMinute());
  }
}
