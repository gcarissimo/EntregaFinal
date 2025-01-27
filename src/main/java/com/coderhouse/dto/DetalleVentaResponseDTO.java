package com.coderhouse.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaResponseDTO {
   private String producto;
   private int cantidad;
   private BigDecimal precioUnitario;
   private BigDecimal subtotal;
}
