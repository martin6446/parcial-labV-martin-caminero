package com.example.parcial.martin.caminero.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Euro {

    private String nombre;
    private String compra;
    private String venta;
}
