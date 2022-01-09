package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class GetViviendaInteresadoDto extends GetViviendaImprescindibles{

    private boolean interes;
}
