package com.salesianostriana.dam.ProyectoRecuperacion.users.dto.propietario;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda.GetViviendaConPropietarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class GetUsuarioPropietarioDto extends GetUsuarioDto {

    private String direccion;
    private String telefono;

    @Builder.Default
    private List<GetViviendaConPropietarioDto> viviendaConPropietario = new ArrayList<>();
}
