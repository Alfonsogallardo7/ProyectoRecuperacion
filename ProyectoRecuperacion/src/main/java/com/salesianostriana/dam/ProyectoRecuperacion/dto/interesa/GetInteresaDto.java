package com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa;

import com.salesianostriana.dam.ProyectoRecuperacion.models.InteresaPK;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class GetInteresaDto {

    private InteresaPK id;
    private String vivienda;
    private String interesado;
    private LocalDateTime createDate;
    private String mensaje;
}
