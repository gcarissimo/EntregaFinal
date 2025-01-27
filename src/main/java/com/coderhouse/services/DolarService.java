package com.coderhouse.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderhouse.dto.DolarResponseDTO;


@Service
public class DolarService {

    @Autowired
    private RestTemplate restTemplate;

    public DolarResponseDTO obtenerCotizacion(String casa, String fecha) {
        try {
            // Dividir la fecha en el formato requerido (YYYY/MM/DD)
            String[] fechaPartes = fecha.split("-");
            if (fechaPartes.length != 3) {
                throw new IllegalArgumentException("El formato de fecha debe ser YYYY-MM-DD");
            }
            String fechaFormateada = String.join("/", fechaPartes);

            // Construir la URL final
            final String baseUrl = "https://api.argentinadatos.com/v1/cotizaciones/dolares/{casa}/{fecha}";
            String finalUrl = UriComponentsBuilder
                    .fromUriString(baseUrl)
                    .buildAndExpand(Map.of("casa", casa, "fecha", fechaFormateada))
                    .toUriString();

            // Log para depuración
            System.out.println("Llamando a la URL: " + finalUrl);

            // Llamar a la API externa
            return restTemplate.getForObject(finalUrl, DolarResponseDTO.class);
        } catch (RestClientException e) {
            System.err.println("Error, no se pudo conectar a la API externa: " + e.getMessage());
            return null;
        }
    }
}

