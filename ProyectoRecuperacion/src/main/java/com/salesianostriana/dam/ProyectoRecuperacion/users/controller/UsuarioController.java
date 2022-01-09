package com.salesianostriana.dam.ProyectoRecuperacion.users.controller;

import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.CreateUsuarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.UsuarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioDtoConverter usuarioDtoConverter;

    @PostMapping("/register/user")
    public ResponseEntity<GetUsuarioDto> addPropietario (@RequestBody CreateUsuarioDto nuevoUsuario) {
        Usuario usuario = usuarioService.savePropietario(nuevoUsuario);

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        } else
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDtoConverter.converterUsuarioToUsuarioDto(usuario));
    }

    @PostMapping("/register/gestor")
    public ResponseEntity<GetUsuarioDto> addGestor (@RequestBody CreateUsuarioDto nuevoUsuario) {
        Usuario usuario = usuarioService.saveGestor(nuevoUsuario);

        if (usuario == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDtoConverter.converterUsuarioToUsuarioDto(usuario));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<GetUsuarioDto> addAdmin (@RequestBody CreateUsuarioDto nuevoUsuario) {

        Usuario usuario = usuarioService.saveAdmin(nuevoUsuario);

        if(usuario == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDtoConverter.converterUsuarioToUsuarioDto(usuario));
    }
}
