package com.seruax.ventasservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class VentaDTO {

    private Long id;
    private LocalDate fecha;
    private Float total;
    private List<ProductoDTO> listaProductos;

}
