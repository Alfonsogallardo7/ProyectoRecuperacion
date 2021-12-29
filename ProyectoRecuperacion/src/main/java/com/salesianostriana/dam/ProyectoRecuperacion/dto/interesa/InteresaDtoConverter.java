package com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Interesa;
import org.springframework.stereotype.Component;

@Component
public class InteresaDtoConverter {

    public GetInteresaDto convertInteresaToInteresaDto(Interesa interesa) {
        return GetInteresaDto.builder()
                .vivienda(interesa.getVivienda().getTitulo())
                .interesado(interesa.getUsuario().getNombre())
                .createDate(interesa.getCreatedDate())
                .mensaje(interesa.getMensaje())
                .build();
    }
}
