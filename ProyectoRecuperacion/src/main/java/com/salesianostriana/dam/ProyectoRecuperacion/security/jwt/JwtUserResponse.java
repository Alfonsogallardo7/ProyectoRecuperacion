package com.salesianostriana.dam.ProyectoRecuperacion.security.jwt;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class JwtUserResponse {

    private String nombre, apellidos, email, avatar, rol, token;
}
