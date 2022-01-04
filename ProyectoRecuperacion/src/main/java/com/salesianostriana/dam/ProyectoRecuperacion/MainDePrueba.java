package com.salesianostriana.dam.ProyectoRecuperacion;

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
                .role(UserRole.GESTOR)
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

        usuarioService.save(usuario1);
        usuarioService.save(usuario2);
        usuarioService.save(usuario3);
    }

}
