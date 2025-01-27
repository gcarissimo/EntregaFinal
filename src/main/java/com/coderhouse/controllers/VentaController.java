package com.coderhouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.dto.VentaDTO;
import com.coderhouse.dto.VentaResponseDTO;
import com.coderhouse.models.Venta;
import com.coderhouse.repositories.VentaRepository;
import com.coderhouse.services.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private VentaService ventaService;
	
	@GetMapping
	public List<Venta> getAllVentas(){
		return ventaRepository.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Venta> getVentaById(@PathVariable Long id){
		if(ventaRepository.existsById(id)) {
			Venta venta = ventaRepository.findById(id).get();
			return ResponseEntity.ok(venta);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@PostMapping
	public ResponseEntity<VentaResponseDTO> createVenta(@RequestBody VentaDTO ventaDTO) {
	    return ResponseEntity.ok(ventaService.createVenta(ventaDTO));
	}
}
