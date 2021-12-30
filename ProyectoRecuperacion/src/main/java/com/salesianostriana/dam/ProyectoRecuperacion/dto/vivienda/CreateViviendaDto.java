package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CreateViviendaDto {

    private String titulo, descripcion, avatar, lating, poblacion, codigoPostal, provincia, tipo, direccion;
    private Double precio, metrosCuadrados;
    private int numHabitacion, numBanios;
    private boolean piscina, garaje, ascensor;
}
