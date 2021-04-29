package se.kth.iv1350.danielhenning.dto;

public class ItemInformationDTO {
    private double price;   //ItemPrice
    private double vatRate; // ItemPrice
    private String itemDescription; // ItemDescription
    private String itemIdentifier;  // ItemDesription
/**
 * Creates an ItemInformationDTO with the data taken from database for the scanned itemcode.
 * @param price is double representing the price of the item.
 * @param vatRate is a double representing the amount of vat on the item.
 * @param itemDescription a String representing the name of the item.
 * @param itemIdentifier a String representing the items identifier that was scanned.
 */
    public ItemInformationDTO(double price, double vatRate, String itemDescription, String itemIdentifier) {
        this.price = price;
        this.vatRate = vatRate;
        this.itemDescription = itemDescription;
        this.itemIdentifier = itemIdentifier;
    }

    /*
    public ItemInformationDTO(ItemInformationDTO itemInformationDTO, int quantity, double discount){
        this.price = itemInformationDTO.getPrice();
        this.vatRate = itemInformationDTO.getVATrate();
        this.itemDescription = itemInformationDTO.getItemDescription();
        this.itemIdentifier = itemInformationDTO.getItemIdentifier();
    }*/
    
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
}