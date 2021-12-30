package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class GetViviendaDto {

    private String titulo, descripcion, avatar, lating, poblacion, codigoPostal, provincia, tipo, direccion;
    private Double precio, metrosCuadrados;
    private int numHabitacion, numBanios;
    private boolean piscina, garaje, ascensor;
    private String nombrePropietario, emailPropietario, telefonoPropietario, inmobiliaria;

}
