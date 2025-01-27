package com.coderhouse.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DolarResponseDTO {
    private String casa;
    private String fecha;
    private BigDecimal compra;
    private BigDecimal venta;
    
}
