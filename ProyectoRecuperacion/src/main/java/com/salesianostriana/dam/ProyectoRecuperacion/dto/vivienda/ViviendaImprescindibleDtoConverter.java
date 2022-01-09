package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaImprescindibleDtoConverter {

    public GetViviendaImprescindibles convertViviendaToViviendaImprescindibleDto(Vivienda vivienda) {
        return GetViviendaImprescindibles.builder()
                .id(vivienda.getId())
                .titulo(vivienda.getTitulo())
                .tipo(vivienda.getTipo())
                .avatar(vivienda.getAvatar())
                .direccion(vivienda.getDireccion())
                .precio(vivienda.getPrecio())
                .nombrePropietario(vivienda.getUsuario().getNombre())
                .avatarPropietario(vivienda.getUsuario().getAvatar())
                .nombreInmobiliaria(vivienda.getInmobiliaria() == null ? "Sin inmobiliaria" : vivienda.getInmobiliaria().getNombre())
                .build();
    }
}
