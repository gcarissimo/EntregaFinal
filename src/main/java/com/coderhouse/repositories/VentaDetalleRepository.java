package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Venta_Detalle;

public interface VentaDetalleRepository extends JpaRepository<Venta_Detalle, Long> {
}
