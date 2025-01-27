package com.coderhouse.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dto.ClienteResumenDTO;
import com.coderhouse.dto.DetalleVentaDTO;
import com.coderhouse.dto.DetalleVentaResponseDTO;
import com.coderhouse.dto.VentaDTO;
import com.coderhouse.dto.VentaResponseDTO;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;
import com.coderhouse.models.Venta_Detalle;
import com.coderhouse.repositories.ClienteRepository;
import com.coderhouse.repositories.ProductoRepository;
import com.coderhouse.repositories.VentaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VentaService {
    private static final BigDecimal IVA = new BigDecimal("0.21");
    
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public VentaResponseDTO createVenta(VentaDTO ventaDTO) {
        Cliente cliente = clienteRepository.findById(ventaDTO.getIdCliente())
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        
        Venta venta = new Venta(cliente, LocalDateTime.now(), BigDecimal.ZERO);
        
        for (DetalleVentaDTO detalleDTO : ventaDTO.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
            
            Venta_Detalle detalle = new Venta_Detalle(producto, detalleDTO.getCantidad());
            venta.agregarDetalle(detalle);
        }
        
        venta.calcularTotal();
        venta = ventaRepository.save(venta);
        
        return convertToResponseDTO(venta);
    }

    private VentaResponseDTO convertToResponseDTO(Venta venta) {
        List<DetalleVentaResponseDTO> detalles = venta.getDetalles().stream()
            .map(d -> new DetalleVentaResponseDTO(
                d.getProducto().getNombre(),
                d.getCantidad(),
                d.getPrecioUnitario(),
                d.getSubtotal()
            ))
            .collect(Collectors.toList());

        return new VentaResponseDTO(
            venta.getIdVenta(),
            venta.getFechaVenta().toString(),
            new ClienteResumenDTO(
                venta.getCliente().getNombre(),
                venta.getCliente().getApellido(),
                venta.getCliente().getEmail()
            ),
            detalles,
            venta.getTotalVta(),
            venta.getTotalVta().multiply(IVA),
            venta.getTotalVta().multiply(BigDecimal.ONE.add(IVA))
        );
    }
}