package com.coderhouse.models;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Schema(description = "Modelo de cliente")
@Entity
@Table(name = "productos")
public class Producto {
	
	@Schema(description = "ID de producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
	private long idProducto;
	
	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Celular")
	@Column(name = "Nombre")
	private String nombre;
	
	@Schema(description = "Descripción del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "15 Pro MAX")
	@Column(name = "Descipcion")
	private String descripcion;
	
	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1.00")
	@Column(name = "Precio")
	private BigDecimal precio;
	
	public Producto() {
		super();
	}

	public Producto(String nombre, String descripcion, BigDecimal precio) {
		this();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}	
	
	
}
