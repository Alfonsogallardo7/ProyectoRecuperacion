package com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda.ViviendaConPropietarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Inmobiliaria;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.UsuarioDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InmobiliariaDtoConverter {

    private final ViviendaConPropietarioDtoConverter viviendaConPropietarioDtoConverter;
    private final UsuarioDtoConverter usuarioDtoConverter;

    public GetInmobiliariaDto convertInmobiliariaToGetInmobiliariaDto (Inmobiliaria inmobiliaria) {
        return GetInmobiliariaDto.builder()
                .id(inmobiliaria.getId())
                .nombre(inmobiliaria.getNombre())
                .email(inmobiliaria.getEmail())
                .telefono(inmobiliaria.getTelefono())
                .listaViviendas(inmobiliaria.getListaVivienda()
                        .stream()
                        .map(viviendaConPropietarioDtoConverter::convertViviendaConPropietarioToViviendaConPropietarioDto)
                        .collect(Collectors.toList()))
                .gestor(inmobiliaria.getListaGestores()
                        .stream()
                        .map(usuarioDtoConverter::converterUsuarioToUsuarioDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
