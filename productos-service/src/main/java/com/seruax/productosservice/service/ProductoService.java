package com.seruax.productosservice.service;

import com.seruax.productosservice.model.Producto;
import com.seruax.productosservice.repository.IProductoRepository;
import com.seruax.productosservice.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    IProductoRepository productoRepository;

//    /* Para probar el balanceador de carga Round Robin -> */
//    @Value("${server.port}")
//    private int serverPort;
//   /* <- Para probar el balanceador de carga Round Robin */

    @Override
    public void create(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto get(Long codigo) {
//        System.out.println("------------- Estoy en el puerto: " + serverPort);
        return productoRepository.findById(codigo).orElse(null);
    }

    @Override
    public void update(Producto producto) {
        this.create(producto);
    }

    @Override
    public void delete(Long codigo) {
        productoRepository.deleteById(codigo);
    }

    // Get the sum prices of the  products list
    @Override
    public Float getTotalPrice(List<Long> codigosList) {
        Double total = productoRepository.totalCarritoPrice(codigosList);
        // Redondeamos a 2 decimales
        return (float) (Math.round(total * 100) / 100d);
    }
}
