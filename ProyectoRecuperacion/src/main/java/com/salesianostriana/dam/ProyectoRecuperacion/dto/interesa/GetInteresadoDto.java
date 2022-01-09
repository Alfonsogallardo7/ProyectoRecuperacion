package com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa;

import com.salesianostriana.dam.ProyectoRecuperacion.models.InteresaPK;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class GetInteresadoDto {

    private InteresaPK id;
    private String vivienda;
    private String interesado;
    private LocalDateTime createDate;
    private String mensaje;
}
