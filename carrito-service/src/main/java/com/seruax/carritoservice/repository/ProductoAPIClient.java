package com.seruax.carritoservice.repository;

import com.seruax.carritoservice.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "productos-service")
public interface ProductoAPIClient {

    @GetMapping("/productos/get/{codigo}")
    public ProductoDTO getProducto(@PathVariable Long codigo);

    @GetMapping("/productos/totalprice/{codigosList}")
    public Float getTotalPrice(@PathVariable List<Long> codigosList);

}
