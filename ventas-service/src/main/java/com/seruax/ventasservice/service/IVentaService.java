package com.seruax.ventasservice.service;

import com.seruax.ventasservice.dto.VentaDTO;
import com.seruax.ventasservice.model.Venta;

import java.util.List;

public interface IVentaService {

    void create(Venta venta);

    List<Venta> getAll();

    Venta get(Long id);

    void update(Venta venta);

    void delete(Long id);

    VentaDTO convertToVentaDTO(Venta venta);

    VentaDTO getVenta(Long id);

    List<VentaDTO> getAllVentas();

}
