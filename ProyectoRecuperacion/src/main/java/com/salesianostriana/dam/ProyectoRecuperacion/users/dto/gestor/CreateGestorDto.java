package com.salesianostriana.dam.ProyectoRecuperacion.users.dto.gestor;

import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.CreateUsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@SuperBuilder
public class CreateGestorDto extends CreateUsuarioDto {

    private UUID idInmobiliaria;
}
