package com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import lombok.*;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter@Setter
public class CreateInteresDto {

    private Usuario usuario;
    private Vivienda vivienda;
    private LocalDateTime createDate;
    private String mensaje;
}
