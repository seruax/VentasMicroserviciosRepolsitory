package com.seruax.carritoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CarritoDTO {

    private Long id;
    private Float total;
    private List<ProductoDTO> listaProductos;

}
