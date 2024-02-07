package com.seruax.ventasservice.repository;

import com.seruax.ventasservice.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrito-service")
public interface CarritoAPIClient {

    @GetMapping("/carrito/get/{id}")
    public CarritoDTO getCarrito(@PathVariable Long id);

}
