package com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class CreateInmobiliariaDto {

    private String nombre, email, telefono;
}
