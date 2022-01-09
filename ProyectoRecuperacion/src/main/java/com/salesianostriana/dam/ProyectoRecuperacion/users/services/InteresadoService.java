package com.salesianostriana.dam.ProyectoRecuperacion.users.services;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa.CreateInteresDto;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa.CreateInteresaInmprescindibleDto;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Interesa;
import com.salesianostriana.dam.ProyectoRecuperacion.models.InteresaPK;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import com.salesianostriana.dam.ProyectoRecuperacion.repositories.InteresaRepository;
import com.salesianostriana.dam.ProyectoRecuperacion.services.base.BaseService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InteresadoService extends BaseService<Interesa, InteresaPK, InteresaRepository> {


    public Interesa saveInteresado(CreateInteresDto createInteresDto, CreateInteresaInmprescindibleDto mensaje,
                                   Usuario user, Vivienda vivienda){
        Interesa interesa = Interesa.builder()
                .usuario(user)
                .vivienda(vivienda)
                .createdDate(LocalDateTime.now())
                .mensaje(mensaje.getMensaje())
                .build();
        return save(interesa);
    }


}
