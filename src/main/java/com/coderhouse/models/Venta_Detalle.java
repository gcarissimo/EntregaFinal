package com.coderhouse.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Schema(description = "Detalle de la venta donde se listan los productos vendidos")
@Entity
@Table(name = "venta_detalle")
public class Venta_Detalle {
	
	
	@Schema(description = "ID del detalle de ventas", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;
	
	@Schema(description = "Cantidad del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Column(name = "cantidad")
	private int cantidad;
	
	@Schema(description = "Precio unitario del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "999.99")
	@Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
	
	@Schema(description = "Subtotal de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "999.99")
	@Column(name = "subtotal")
    private BigDecimal subtotal;
	
	@ManyToOne
    @JoinColumn(name = "id_venta")
	@JsonIgnore
    private Venta venta;
	
	@ManyToOne
    @JoinColumn(name = "id_producto")
	@JsonIgnore
    private Producto producto;
	
	public Venta_Detalle() {
		super();
	}
	
	public Venta_Detalle(Producto producto, int cantidad) {
	    this.producto = producto;
	    this.cantidad = cantidad;
	    this.precioUnitario = producto.getPrecio();
	    this.subtotal = calcularSubtotal();
	}
	
	public BigDecimal calcularSubtotal() {
	    return precioUnitario.multiply(new BigDecimal(cantidad));
	}

	public long getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(long idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@Override
	public String toString() {
		return "Venta_Detalle [idDetalle=" + idDetalle + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario
				+ ", subtotal=" + subtotal + ", venta=" + venta + ", producto=" + producto + "]";
	}	
}
