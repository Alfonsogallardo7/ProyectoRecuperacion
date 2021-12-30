package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaConPropietarioDtoConverter {

    public GetViviendaConPropietarioDto convertViviendaConPropietarioToViviendaConPropietarioDto (Vivienda vivienda) {
        return GetViviendaConPropietarioDto.builder()
                .id(vivienda.getId())
                .titulo(vivienda.getTitulo())
                .precio(vivienda.getPrecio())
                .tipo(vivienda.getTipo())
                .build();
    }
}
