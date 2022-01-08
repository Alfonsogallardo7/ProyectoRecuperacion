package com.salesianostriana.dam.ProyectoRecuperacion.services;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria.CreateInmobiliariaDto;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Inmobiliaria;
import com.salesianostriana.dam.ProyectoRecuperacion.repositories.InmobiliariaRepository;
import com.salesianostriana.dam.ProyectoRecuperacion.services.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InmobiliariaService extends BaseService<Inmobiliaria, UUID, InmobiliariaRepository> {

    public Inmobiliaria saveInmobiliaria (CreateInmobiliariaDto inmobiliariaDto) {
        Inmobiliaria inmobiliaria = Inmobiliaria.builder()
                .nombre(inmobiliariaDto.getNombre())
                .email(inmobiliariaDto.getEmail())
                .telefono(inmobiliariaDto.getTelefono())
                .build();

        return save(inmobiliaria);
    }
}
