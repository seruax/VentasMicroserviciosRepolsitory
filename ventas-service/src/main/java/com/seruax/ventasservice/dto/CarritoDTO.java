package com.seruax.ventasservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CarritoDTO {

    private Long id;
    private Float total;
    private List<ProductoDTO> listaProductos;

}
