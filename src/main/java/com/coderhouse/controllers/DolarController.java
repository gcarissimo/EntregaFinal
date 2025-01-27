package com.coderhouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.DolarResponseDTO;
import com.coderhouse.services.DolarService;

@RestController
@RequestMapping("/api/dolar")
public class DolarController {

    @Autowired
    private DolarService dolarService;

    @GetMapping
    public ResponseEntity<String> obtenerCotizacion(
            @RequestParam String casa,
            @RequestParam String fecha) {

        if (casa == null || casa.isEmpty() || fecha == null || fecha.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Error: Los parámetros 'casa' y 'fecha' son obligatorios.");
        }

        try {
            DolarResponseDTO dolarCotizacion = dolarService.obtenerCotizacion(casa, fecha);
            if (dolarCotizacion == null || dolarCotizacion.getCasa() == null) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Error, no se pudo obtener la cotización del dólar.");
            }

            String mensaje = String.format(
                    "La cotización del %s para el día %s:\nTipo comprador: %s\nTipo vendedor: %s",
                    dolarCotizacion.getCasa(),
                    dolarCotizacion.getFecha(),
                    dolarCotizacion.getCompra(),
                    dolarCotizacion.getVenta()
            );

            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado: " + e.getMessage());
        }
    }
}
