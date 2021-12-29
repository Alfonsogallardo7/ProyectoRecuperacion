package com.salesianostriana.dam.ProyectoRecuperacion.users.dto;

import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConverter {

    public GetUsuarioDto converterUsuarioToUsuarioDto (Usuario usuario) {
        return GetUsuarioDto.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .avatar(usuario.getAvatar())
                .email(usuario.getEmail())
                .direccion(usuario.getDireccion())
                .rol(usuario.getRol().name())
                .telefono(usuario.getTelefono())
                .build();
    }
}
