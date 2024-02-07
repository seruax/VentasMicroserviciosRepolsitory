package com.seruax.productosservice.repository;

import com.seruax.productosservice.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT SUM (p.precio) FROM Producto p WHERE p.codigo IN :codigosList")
    Double totalCarritoPrice(List<Long> codigosList);

}
