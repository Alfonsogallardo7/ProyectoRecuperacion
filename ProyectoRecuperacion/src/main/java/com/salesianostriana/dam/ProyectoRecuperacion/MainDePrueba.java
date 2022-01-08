package com.salesianostriana.dam.ProyectoRecuperacion;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Inmobiliaria;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import com.salesianostriana.dam.ProyectoRecuperacion.services.InmobiliariaService;
import com.salesianostriana.dam.ProyectoRecuperacion.services.ViviendaService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.UserRole;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MainDePrueba {

    private final UsuarioService usuarioService;
    private final ViviendaService viviendaService;
    private final InmobiliariaService inmobiliariaService;

    @PostConstruct
    public void datosPrueba () {

        Usuario usuario1 = Usuario.builder()
                .nombre("Alfonso")
                .apellidos("Gallardo Rodríguez")
                .avatar("foto.png")
                .direccion("Canarias 114")
                .email("alfonso@gmail.com")
                .password("Admin1")
                .telefono("123456789")
                .role(UserRole.ADMIN)
                .build();

        Usuario usuario2 = Usuario.builder()
                .nombre("Pepe")
                .apellidos("Gallardo Rodríguez")
                .avatar("foto.png")
                .direccion("Canarias 114")
                .email("pepe@gmail.com")
                .password("Admin1")
                .telefono("123456789")
                .role(UserRole.PROPIETARIO)
                .build();

        Usuario usuario3 = Usuario.builder()
                .nombre("Alfonso")
                .apellidos("Gallardo Fresno")
                .avatar("foto.png")
                .direccion("Canarias 114")
                .email("alfonsogallardo@gmail.com")
                .password("Admin1")
                .telefono("123456789")
                .role(UserRole.PROPIETARIO)
                .build();

        Inmobiliaria inmobiliaria1 = Inmobiliaria.builder()
                .nombre("Tecnocasa")
                .email("tecnocasa@gmail.com")
                .telefono("657345234")
                .build();

        inmobiliariaService.save(inmobiliaria1);


        Vivienda vivienda1 = Vivienda.builder()
                .titulo("Casa Adosada")
                .descripcion("Casa adosada en la barriada San José Obrero, localizada en el municipio de San Juan de Aznalfarache, Sevilla")
                .avatar("foto.png")
                .lating("45.5346,-45.34654")
                .direccion("Canarias 114")
                .codigoPostal("41920")
                .poblacion("San Juan de Aznalfarache")
                .provincia("Sevilla")
                .tipo("Casa")
                .usuario(usuario3)
                .inmobiliaria(inmobiliaria1)
                .precio(143345.00)
                .numHabitaciones(3)
                .numBanios(2)
                .piscina(false)
                .ascensor(false)
                .garaje(false)
                .build();

        Vivienda vivienda2 = Vivienda.builder()
                .titulo("Pisito")
                .descripcion("Casa adosada en la barriada San José Obrero, localizada en el municipio de San Juan de Aznalfarache, Sevilla")
                .avatar("foto.png")
                .lating("45.5346,-45.34654")
                .direccion("Canarias 114")
                .codigoPostal("41920")
                .poblacion("San Juan de Aznalfarache")
                .provincia("Sevilla")
                .tipo("Casa")
                .usuario(usuario3)
                .inmobiliaria(inmobiliaria1)
                .precio(143345.00)
                .numHabitaciones(3)
                .numBanios(2)
                .piscina(false)
                .ascensor(false)
                .garaje(false)
                .build();

        usuarioService.save(usuario1);
        usuarioService.save(usuario2);
        usuarioService.save(usuario3);
        viviendaService.save(vivienda1);
        viviendaService.save(vivienda2);
    }

}
