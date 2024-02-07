package com.seruax.productosservice.controller;

import com.seruax.productosservice.model.Producto;
import com.seruax.productosservice.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping("/create")
    public void create(@RequestBody Producto producto) {
        productoService.create(producto);
    }

    @GetMapping("/getall")
    public List<Producto> getAll() {
        return productoService.getAll();
    }

    @GetMapping("/get/{codigo}")
    public Producto get(@PathVariable Long codigo) {
        return productoService.get(codigo);
    }

    @PutMapping("/update")
    public void update(@RequestBody Producto producto) {
        productoService.update(producto);
    }

    @DeleteMapping("/delete/{codigo}")
    public void delete(@PathVariable Long codigo) {
        productoService.delete(codigo);
    }

    @GetMapping("/totalprice/{codigosList}")
    public Float getTotalPrice(@PathVariable List<Long> codigosList){
        return productoService.getTotalPrice(codigosList);
    }


}
