package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetViviendaConPropietarioDto {

    private UUID id;
    private String titulo, tipo;
    private double precio;
}
