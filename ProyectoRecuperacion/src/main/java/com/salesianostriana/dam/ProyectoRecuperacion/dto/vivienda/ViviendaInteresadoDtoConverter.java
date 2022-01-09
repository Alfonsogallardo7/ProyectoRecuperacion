package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaInteresadoDtoConverter {

    public GetViviendaInteresadoDto convetViviendaToViviendaInteresadoDto(Vivienda vivienda) {
        return GetViviendaInteresadoDto.builder()
                .id(vivienda.getId())
                .titulo(vivienda.getTitulo())
                .avatar(vivienda.getAvatar())
                .precio(vivienda.getPrecio())
                .nombrePropietario(vivienda.getUsuario().getNombre())
                .avatarPropietario(vivienda.getUsuario().getAvatar())
                .build();
    }
}
