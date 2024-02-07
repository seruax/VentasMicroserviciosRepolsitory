package com.seruax.ventasservice.service;

import com.seruax.ventasservice.dto.CarritoDTO;
import com.seruax.ventasservice.dto.VentaDTO;
import com.seruax.ventasservice.model.Venta;
import com.seruax.ventasservice.repository.CarritoAPIClient;
import com.seruax.ventasservice.repository.IVentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    IVentaRepository ventaRepository;

    @Autowired
    CarritoAPIClient carritoAPIClient;

    @Override
    public void create(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public List<Venta> getAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta get(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Venta venta) {
        this.create(venta);
    }

    @Override
    public void delete(Long id) {
        ventaRepository.deleteById(id);
    }

    // return a VentaDTO
    @Override
    @CircuitBreaker(name= "carrito-service", fallbackMethod = "fallbackGetVenta")
    public VentaDTO getVenta(Long id) {
        Venta venta = this.get(id);
        return this.convertToVentaDTO(venta);
    }

    // return all VentaDTO
    @Override
    @CircuitBreaker(name= "carrito-service", fallbackMethod = "fallbackGetAllVentas")
    public List<VentaDTO> getAllVentas() {
        List<VentaDTO> listaVentas = new ArrayList<>();
        for (Venta venta: ventaRepository.findAll()){
            listaVentas.add(this.convertToVentaDTO(venta));
        }
        return listaVentas;
    }

    // convert data to VentaDTO
    @Override
    public VentaDTO convertToVentaDTO(Venta venta) {
        VentaDTO ventaDTO = new VentaDTO();
        CarritoDTO carritoDTO = carritoAPIClient.getCarrito(venta.getCarritoId());

        ventaDTO.setId(venta.getId());
        ventaDTO.setFecha(venta.getFecha());
        ventaDTO.setListaProductos(carritoDTO.getListaProductos());
        ventaDTO.setTotal(carritoDTO.getTotal());
        return ventaDTO;
    }

    // Cricuit Breaker fallback methods
    public VentaDTO fallbackGetVenta(Throwable throwable){
        return new VentaDTO(999999L, null, 999999F, null);
    }

    public List<VentaDTO> fallbackGetAllVentas(Throwable throwable){
        return Collections.singletonList(new VentaDTO(999999L, null, 999999F, null));
    }

    // Method to simulate an exception and test the circuit breaker. This method should be placed in a method that calls convertToVentaDTO().
    public void createException(){
        throw new IllegalArgumentException("Prueba Resilience y Circuit Breaker");
    }

}
