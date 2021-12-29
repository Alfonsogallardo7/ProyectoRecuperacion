package com.salesianostriana.dam.ProyectoRecuperacion.users.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class GetUsuarioDto {

    private String nombre, apellidos, email, avatar, telefono, rol, direccion;

}
