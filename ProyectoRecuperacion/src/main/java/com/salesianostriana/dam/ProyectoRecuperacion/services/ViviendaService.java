package com.salesianostriana.dam.ProyectoRecuperacion.services;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda.CreateViviendaDto;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import com.salesianostriana.dam.ProyectoRecuperacion.repositories.ViviendaRepository;
import com.salesianostriana.dam.ProyectoRecuperacion.services.base.BaseService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViviendaService extends BaseService<Vivienda, UUID, ViviendaRepository> {

    public Vivienda saveViviendaConPropietario (CreateViviendaDto nuevaVivienda, Usuario usuario) {
        Vivienda vivienda = Vivienda.builder()
                .titulo(nuevaVivienda.getTitulo())
                .avatar(nuevaVivienda.getAvatar())
                .descripcion(nuevaVivienda.getDescripcion())
                .direccion(nuevaVivienda.getDireccion())
                .poblacion(nuevaVivienda.getPoblacion())
                .provincia(nuevaVivienda.getProvincia())
                .codigoPostal(nuevaVivienda.getCodigoPostal())
                .lating(nuevaVivienda.getLating())
                .metrosCuadrados(nuevaVivienda.getMetrosCuadrados())
                .garaje(nuevaVivienda.isGaraje())
                .numBanios(nuevaVivienda.getNumBanios())
                .numHabitaciones(nuevaVivienda.getNumHabitacion())
                .precio(nuevaVivienda.getPrecio())
                .ascensor(nuevaVivienda.isAscensor())
                .piscina(nuevaVivienda.isPiscina())
                .usuario(usuario)
                .tipo(nuevaVivienda.getTipo())
                .build();
        return save(vivienda);
    }
}
