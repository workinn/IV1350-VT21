package src.DTO;

public class ItemInformationDTO {
    private float price;
    private float vatRate;    
    private String itemDescription;
    private String itemIdentifier;
    private float discount;
    private int quantity;

    public ItemInformationDTO(float price, float vatRate, String itemDescription, String itemIdentifier, float discount) {
        this.price = price;
        this.vatRate = vatRate;
        this.itemDescription = itemDescription;
        this.itemIdentifier = itemIdentifier;
        this.discount = discount;
        this.quantity = 1;
    }

    public ItemInformationDTO(ItemInformationDTO itemInformationDTO, int quantity, float discount){
        this.price = itemInformationDTO.getPrice();
        this.vatRate = itemInformationDTO.getVATrate();
        this.itemDescription = itemInformationDTO.getItemDescription();
        this.itemIdentifier = itemInformationDTO.getItemIdentifier();
        this.discount = discount;
        this.quantity = quantity;

    }
    
    public float getPrice(){
        return price;
    }
    
    public float getVATrate(){
        return vatRate;
    }
    
    public String getItemDescription(){
        return itemDescription;
    }

    public String getItemIdentifier(){
        return itemIdentifier;
    }

    public float getDiscount(){
        return discount;
    }

    public int getQuantity(){
        return quantity;
    }
}