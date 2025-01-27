package com.coderhouse.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponseDTO {
	private Long numeroVenta;
	private String fechaVenta;
	private ClienteResumenDTO cliente;
	private List<DetalleVentaResponseDTO> items;
	private BigDecimal subtotal;
	private BigDecimal iva;
	private BigDecimal total;
}
