package se.kth.iv1350.danielhenning.dto;

public final class ItemInformationDTO {
    private double price;
    private double vatRate;
    private String itemDescription;
    private String itemIdentifier;

    public ItemInformationDTO(double price, double vatRate, String itemDescription, String itemIdentifier) {
        this.price = price;
        this.vatRate = vatRate;
        this.itemDescription = itemDescription;
        this.itemIdentifier = itemIdentifier;
    }
    
    public double getPrice(){
        return price;
    }
    
    public double getVATrate(){
        return vatRate;
    }
    
    public String getItemDescription(){
        return itemDescription;
    }

    public String getItemIdentifier(){
        return itemIdentifier;
    }

    public String toString() {
        return itemDescription + "   |  " + itemIdentifier;
    }
}