package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa.GetInteresadoDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class GetViviendaDto {

    private String titulo, descripcion, avatar, lating, poblacion, codigoPostal, provincia, tipo, direccion;
    private Double precio, metrosCuadrados;
    private int numHabitacion, numBanios;
    private boolean piscina, garaje, ascensor;
    private String nombrePropietario, emailPropietario, telefonoPropietario, inmobiliaria;

    @Builder.Default
    private List<GetInteresadoDto> interesado = new ArrayList<>();

}
