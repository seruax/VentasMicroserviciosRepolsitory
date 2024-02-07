package com.seruax.ventasservice.controller;

import com.seruax.ventasservice.dto.VentaDTO;
import com.seruax.ventasservice.model.Venta;
import com.seruax.ventasservice.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    IVentaService ventaService;

    @PostMapping("/create")
    public void create(@RequestBody Venta venta) {
        ventaService.create(venta);
    }

    @GetMapping("/getall")
    public List<Venta> getAll() {
        return ventaService.getAll();
    }

    @GetMapping("/get/{codigo}")
    public Venta get(@PathVariable Long codigo) {
        return ventaService.get(codigo);
    }

    @PutMapping("/update")
    public void update(@RequestBody Venta producto) {
        ventaService.update(producto);
    }

    @DeleteMapping("/delete/{codigo}")
    public void delete(@PathVariable Long codigo) {
        ventaService.delete(codigo);
    }

    @GetMapping("/getventa/{id}")
    public VentaDTO getVenta(@PathVariable Long id){
        return ventaService.getVenta(id);
    }

    @GetMapping("/getallventas")
    public List<VentaDTO> getAllVentas(){
        return ventaService.getAllVentas();
    }

}
