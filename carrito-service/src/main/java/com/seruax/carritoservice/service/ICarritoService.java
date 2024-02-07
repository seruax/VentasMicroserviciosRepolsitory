package com.seruax.carritoservice.service;

import com.seruax.carritoservice.dto.CarritoDTO;
import com.seruax.carritoservice.dto.ProductoDTO;
import com.seruax.carritoservice.model.Carrito;

import java.util.List;

public interface ICarritoService {

    void create(Carrito carrito);

    List<CarritoDTO> getAll();

    CarritoDTO get(Long id);

    void update(Carrito carrito);

    void delete(Long id);

    void addProduct(Long id, Long codigo);

    void removeProduct(Long id, Long codigo);

    CarritoDTO convertToCarritoDTO(Carrito carrito);

    List<ProductoDTO> getListaProductos(List<Long> listaCodigosProductos);

    Float getTotalPrice(List<Long> codigosList);
}
