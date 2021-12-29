package com.salesianostriana.dam.ProyectoRecuperacion.users.dto;

import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CreateUsuarioDto {

    private String nombre, apellidos, avatar, email, telefono, password, password2, direccion;
}
