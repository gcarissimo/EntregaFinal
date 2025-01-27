package com.coderhouse.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Ventas")
@Entity
@Table(name = "Ventas")
public class Venta {
	
	@Schema(description = "ID de ventas", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idVenta;
	
	@Schema(description = "Fecha de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "dd/mm/aaaa")
	@Column(name = "FechaVenta")
	private LocalDateTime fechaVenta;
	
	@Schema(description = "Total de la venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "10.00")
	@Column(name = "TotalVta")
	private BigDecimal totalVta;
	
	@Schema(description = "Cliente al que se le efectuó la venta")
	@ManyToOne
	@JoinColumn(name = "idCliente")
	//@JsonIgnore
	private Cliente cliente;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<Venta_Detalle> detalles = new ArrayList<>();
	
	public Venta() {
		super();
	}
	
	public Venta(Cliente cliente, LocalDateTime fechaVenta, BigDecimal totalVta) {
		this();
		this.cliente = cliente;
		this.fechaVenta = fechaVenta;
		this.totalVta = totalVta;		
	}
	
	public void calcularTotal() {
        BigDecimal total = new BigDecimal("0");
        for (Venta_Detalle detalle : detalles) {
            total = total.add(detalle.calcularSubtotal());
        }
        this.totalVta = total;
    }
	
    public void setDetalles(List<Venta_Detalle> detalles) {
		this.detalles = detalles;
	}

	public void agregarDetalle(Venta_Detalle detalle) {
        detalle.setVenta(this);
        this.detalles.add(detalle);
    }
    
    public List<Venta_Detalle> getDetalles() {
        return detalles;
    }

	public long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(long idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDateTime getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDateTime fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public BigDecimal getTotalVta() {
		return totalVta;
	}

	public void setTotalVta(BigDecimal totalVta) {
		this.totalVta = totalVta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", fechaVenta=" + fechaVenta + ", totalVta=" + totalVta + ", cliente="
				+ cliente + "]";
	}	
}
