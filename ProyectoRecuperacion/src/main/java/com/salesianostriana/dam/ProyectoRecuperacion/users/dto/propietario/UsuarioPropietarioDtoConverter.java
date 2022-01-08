package com.salesianostriana.dam.ProyectoRecuperacion.users.dto.propietario;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda.ViviendaConPropietarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsuarioPropietarioDtoConverter {

    private final ViviendaConPropietarioDtoConverter viviendaConPropietarioDtoConverter;

    public GetUsuarioPropietarioDto convertUsuarioPropietarioToUsuarioPropietarioDto (Usuario usuario) {
        return GetUsuarioPropietarioDto.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .avatar(usuario.getAvatar())
                .rol(usuario.getRole().name())
                .telefono(usuario.getTelefono())
                .direccion(usuario.getDireccion())
                .email(usuario.getEmail())
                .viviendaConPropietario(usuario.getListaVivienda()
                        .stream()
                        .map(viviendaConPropietarioDtoConverter::convertViviendaConPropietarioToViviendaConPropietarioDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
