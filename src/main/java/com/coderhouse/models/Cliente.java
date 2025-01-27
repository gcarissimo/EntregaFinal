package com.coderhouse.models;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Cliente")
@Entity
@Table(name = "Clientes")
public class Cliente {
	
	@Schema(description = "ID de cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
	private long idCliente;
	
	@Schema(description = "Nombre del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Juan")
	@Column(name = "Nombre")
	private String nombre;
	
	@Schema(description = "Apellido del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Perez")
	@Column(name = "Apellido")
	private String apellido;
	
	@Schema(description = "Email del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "juanpere@mail.com")
	@Column(name = "Email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	
	private Cliente() {
		super();
	}
	
	public Cliente(String nombre, String apellido, String email) {
		this();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}
	
	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliene(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	@PrePersist
	protected void onCreate() {
	    createdAt = LocalDateTime.now();
	}

	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", createdAt=" + createdAt + "]";
	}
	
}
