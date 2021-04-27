package se.kth.iv1350.danielhenning.dto;

public class ItemInformationDTO {
    private double price;   //ItemPrice
    private double vatRate; // ItemPrice
    private String itemDescription; // ItemDescription
    private String itemIdentifier;  // ItemDesription
    private double discount;
    private int quantity;

    public ItemInformationDTO(double price, double vatRate, String itemDescription, String itemIdentifier, double discount) {
        this.price = price;
        this.vatRate = vatRate;
        this.itemDescription = itemDescription;
        this.itemIdentifier = itemIdentifier;
        this.discount = discount;
        this.quantity = 1;
    }

    public ItemInformationDTO(ItemInformationDTO itemInformationDTO, int quantity, double discount){
        this.price = itemInformationDTO.getPrice();
        this.vatRate = itemInformationDTO.getVATrate();
        this.itemDescription = itemInformationDTO.getItemDescription();
        this.itemIdentifier = itemInformationDTO.getItemIdentifier();
        this.discount = discount;
        this.quantity = quantity;

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

    public double getDiscount(){
        return discount;
    }

    public int getQuantity(){
        return quantity;
    }
}