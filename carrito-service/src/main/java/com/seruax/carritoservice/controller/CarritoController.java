package com.seruax.carritoservice.controller;

import com.seruax.carritoservice.dto.CarritoDTO;
import com.seruax.carritoservice.model.Carrito;
import com.seruax.carritoservice.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/carrito")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @PostMapping("/create")
    public void create(@RequestBody Carrito carrito) {
        carritoService.create(carrito);
    }

    @GetMapping("/getall")
    public List<CarritoDTO> getAll() {
        return carritoService.getAll();
    }

    @GetMapping("/get/{id}")
    public CarritoDTO get(@PathVariable Long id) {
        return carritoService.get(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Carrito carrito) {
        carritoService.update(carrito);
    }

    @DeleteMapping("/delete/{codigo}")
    public void delete(@PathVariable Long id) {
        carritoService.delete(id);
    }

    @PutMapping("/{idCarrito}/addproduct/{codigoProducto}")
    public void addProduct(@PathVariable Long idCarrito, @PathVariable Long codigoProducto){
        carritoService.addProduct(idCarrito, codigoProducto);
    }

    @PutMapping("/{idCarrito}/removeproduct/{codigoProducto}")
    public void remnoveProduct(@PathVariable Long idCarrito, @PathVariable Long codigoProducto){
        carritoService.removeProduct(idCarrito ,codigoProducto);
    }

}
