package com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda.GetViviendaConPropietarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class GetInmobiliariaDto {

    private UUID id;
    private String nombre, email, telefono;

    @Builder.Default
    private List<GetViviendaConPropietarioDto> listaViviendas = new ArrayList();

    @Builder.Default
    private List<GetUsuarioDto> gestor = new ArrayList<>();
}
