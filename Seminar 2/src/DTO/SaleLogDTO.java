package DTO;

public class SaleLogDTO {

  private SaleDTO sale;
  private String date;
  private String time;

   public SaleLogDTO(SaleDTO sale) {
     this.sale = sale;
     this.date = "2021-04-17";
     this.time = "13:37";
   }

   public SaleDTO getSale() {
     return sale;
   }

   public String getDate() {
     return date;
   }

   public String getTime() {
     return time;
   }
}
