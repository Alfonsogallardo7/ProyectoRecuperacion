package com.salesianostriana.dam.ProyectoRecuperacion.users.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CreateUsuarioDto {

    private String nombre, apellidos, avatar, email, telefono, password, password2, direccion;
}
