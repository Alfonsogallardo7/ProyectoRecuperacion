package com.salesianostriana.dam.ProyectoRecuperacion.users.controller;

import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.UsuarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/propietario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioDtoConverter usuarioDtoConverter;


    @GetMapping("/")
    public ResponseEntity<List<GetUsuarioDto>> findAllPropietarios(Pageable pageable) {
        List<Usuario> usuarios = usuarioService.findAllPropietarios(pageable);

        if (usuarios == null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok()
                    .body(usuarios.stream()
                            .map(usuarioDtoConverter::converterUsuarioToUsuarioDto)
                            .collect(Collectors.toList()))
        }

    }
}
