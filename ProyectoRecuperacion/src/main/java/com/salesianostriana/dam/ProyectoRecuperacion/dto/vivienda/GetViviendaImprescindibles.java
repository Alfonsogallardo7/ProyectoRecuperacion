package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetViviendaImprescindibles {

    private UUID id;
    private String titulo, avatar, direccion, tipo, nombrePropietario, avatarPropietario;
    private Double precio;
}
