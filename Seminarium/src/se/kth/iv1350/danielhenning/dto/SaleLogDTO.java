package se.kth.iv1350.danielhenning.dto;

import java.time.LocalDateTime;

public class SaleLogDTO {

  private SaleDTO sale;
  private LocalDateTime dateTime;

   public SaleLogDTO(SaleDTO sale) {
     this.sale = sale;
     this.dateTime = LocalDateTime.now();
   }

   public SaleDTO getSale() {
     return sale;
   }

   public LocalDateTime getDateTime() {
     return dateTime;
   }
}
