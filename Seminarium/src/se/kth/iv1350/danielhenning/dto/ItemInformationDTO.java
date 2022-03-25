package se.kth.iv1350.danielhenning.dto;

/**
 * The class ItemInformationDTO represents a 
 * Data Transfer Object of an item
 */
public final class ItemInformationDTO {
    private double price;
    private double vatRate;
    private String itemDescription;
    private String itemIdentifier;

    /**
     * Creates a new instance of the class ItemInformationDTO
     * @param price is the price of the item
     * @param vatRate is the VAT rate of the item
     * @param itemDescription is the description of the item
     * @param itemIdentifier is the identifier (bar code) of the item
     */
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
        return itemDescription + "   | " + itemIdentifier;
    }
}