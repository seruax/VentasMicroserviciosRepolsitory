package com.seruax.productosservice.service;

import com.seruax.productosservice.model.Producto;

import java.util.List;

public interface IProductoService {

    void create(Producto producto);

    List<Producto> getAll();

    Producto get(Long codigo);

    void update(Producto producto);

    void delete(Long codigo);

    Float getTotalPrice(List<Long> codigosList);
}
