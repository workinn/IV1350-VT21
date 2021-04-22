package src.DTO;

import java.util.ArrayList;

public class SaleDTO {

  ArrayList<ItemInformationDTO> items;
  float runningTotal;
  boolean lastItemFound;

  public SaleDTO(ArrayList<ItemInformationDTO> items, float runningTotal, boolean lastItemFound) {
    this.items = items;
    this.runningTotal = runningTotal;
    this.lastItemFound = lastItemFound;
  }

  public ArrayList<ItemInformationDTO> getItems() {
    return items;
  }

  public float getRunningTotal() {
    return runningTotal;
  }

  public boolean getLastItemFound() {
    return lastItemFound;
  }
}
