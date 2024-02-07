package com.seruax.carritoservice.service;

import com.seruax.carritoservice.dto.CarritoDTO;
import com.seruax.carritoservice.dto.ProductoDTO;
import com.seruax.carritoservice.model.Carrito;
import com.seruax.carritoservice.repository.ICarritoRepository;
import com.seruax.carritoservice.repository.ProductoAPIClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarritoService implements ICarritoService{

    @Autowired
    ICarritoRepository carritoRepository;

    @Autowired
    ProductoAPIClient productoAPIClient;

    public void create(Carrito carrito) {
        carritoRepository.save(carrito);
    }

    @Override
    @CircuitBreaker(name = "productos-service", fallbackMethod = "fallbackgetAll")
    @Retry(name= "productos-service")
    public List<CarritoDTO> getAll() {
        List<CarritoDTO> listaCarritos = new ArrayList<>();
        for (Carrito carrito: carritoRepository.findAll()){
            listaCarritos.add(this.convertToCarritoDTO(carrito));
        }
        return listaCarritos;
    }

    @Override
    @CircuitBreaker(name = "productos-service", fallbackMethod = "fallbackGet")
    @Retry(name = "productos-service")
    public CarritoDTO get(Long id) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        return this.convertToCarritoDTO(carrito);
    }

    @Override
    public void update(Carrito carrito) {
        this.create(carrito);
    }

    @Override
    public void delete(Long id) {
        carritoRepository.deleteById(id);
    }

    // add a new Product to the Carrito
    @Override
    public void addProduct(Long id, Long codigo) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        if (carrito != null) {
            carrito.getCodigoProducto().add(codigo);
            carritoRepository.save(carrito);
        } else {
            System.out.println("El carrito con id " + id + " no existe.");
        }
    }

    // remove a Product from the Carrito
    @Override
    public void removeProduct(Long id, Long codigo) {
        Carrito carrito = carritoRepository.findById(id).orElse(null);
        if (carrito != null) {
            while (carrito.getCodigoProducto().remove(codigo)) {} // Remove all items that match this codigo
            carritoRepository.save(carrito);
        } else {
            System.out.println("El carrito con id " + id + " no existe.");
        }
    }

    // convert data to CarritoDTO
    @Override
    public CarritoDTO convertToCarritoDTO(Carrito carrito) {
        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setId(carrito.getId());
        carritoDTO.setListaProductos(this.getListaProductos(carrito.getCodigoProducto()));
        carritoDTO.setTotal(this.getTotalPrice(carrito.getCodigoProducto()));
        return carritoDTO;
    }

    // get the products list of the carrito
    @Override
    public List<ProductoDTO> getListaProductos(List<Long> listaCodigosProductos) {
        List<ProductoDTO> listaProductos = new ArrayList<>();
        for (Long codigoProducto: listaCodigosProductos){
            listaProductos.add(productoAPIClient.getProducto(codigoProducto));
        }
        return listaProductos;
    }

    // get the total price of the carrito
    @Override
    public Float getTotalPrice(List<Long> codigosList) {
        return productoAPIClient.getTotalPrice(codigosList);
    }

    // Cricuit Breaker fallback methods
    public CarritoDTO fallbackGet(Throwable throwable){
        return new CarritoDTO(999999L, 999999F, null);
    }

    public List<CarritoDTO> fallbackgetAll(Throwable throwable){
        return Collections.singletonList(new CarritoDTO(999999L, 999999F, null));
    }

    // Method to simulate an exception and test the circuit breaker. This method should be placed in a method that calls ProductoAPIClient like getListaProductos() or getTotalPrice().
    public void createException(){
        throw new IllegalArgumentException("Prueba Resilience y Circuit Breaker");
    }

}
