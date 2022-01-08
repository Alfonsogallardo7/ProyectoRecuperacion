package com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    public GetViviendaDto convertViviendaToViviendaDto (Vivienda vivienda) {
        return GetViviendaDto.builder()
                .titulo(vivienda.getTitulo())
                .codigoPostal(vivienda.getCodigoPostal())
                .descripcion(vivienda.getDescripcion())
                .lating(vivienda.getLating())
                .tipo(vivienda.getTipo())
                .avatar(vivienda.getAvatar())
                .precio(vivienda.getPrecio())
                .direccion(vivienda.getDireccion())
                .metrosCuadrados(vivienda.getMetrosCuadrados())
                .poblacion(vivienda.getPoblacion())
                .ascensor(vivienda.isAscensor())
                .numBanios(vivienda.getNumBanios())
                .numHabitacion(vivienda.getNumHabitaciones())
                .piscina(vivienda.isPiscina())
                .garaje(vivienda.isGaraje())
                .provincia(vivienda.getProvincia())
                .nombrePropietario(vivienda.getUsuario().getNombre())
                .emailPropietario(vivienda.getUsuario().getEmail())
                .telefonoPropietario(vivienda.getUsuario().getTelefono())
                .inmobiliaria(vivienda.getInmobiliaria() == null ? "Sin inmobiliaria" : vivienda.getInmobiliaria().getNombre())
                .build();
    }

    public Vivienda createViviendaDtoToVivienda (CreateViviendaDto createViviendaDto, Vivienda vivienda) {
        return new Vivienda(
                vivienda.getId(),
                createViviendaDto.getTitulo(),
                createViviendaDto.getAvatar(),
                createViviendaDto.getDescripcion(),
                createViviendaDto.getCodigoPostal(),
                createViviendaDto.getLating(),
                createViviendaDto.getDireccion(),
                createViviendaDto.getPoblacion(),
                createViviendaDto.getProvincia(),
                createViviendaDto.getTipo(),
                createViviendaDto.getPrecio(),
                createViviendaDto.getNumHabitacion(),
                createViviendaDto.getMetrosCuadrados(),
                createViviendaDto.getNumBanios(),
                createViviendaDto.isAscensor(),
                createViviendaDto.isGaraje(),
                createViviendaDto.isPiscina(),
                vivienda.getInmobiliaria(),
                vivienda.getUsuario(),
                vivienda.getListaInteresa()

        );
    }
}
