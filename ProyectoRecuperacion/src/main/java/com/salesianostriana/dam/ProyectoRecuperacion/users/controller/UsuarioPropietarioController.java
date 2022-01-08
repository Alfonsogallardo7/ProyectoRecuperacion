package com.salesianostriana.dam.ProyectoRecuperacion.users.controller;

import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.UsuarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.propietario.UsuarioPropietarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.UserRole;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/propietario")
public class UsuarioPropietarioController {

    private final UsuarioService usuarioService;
    private final UsuarioDtoConverter usuarioDtoConverter;
    private final UsuarioPropietarioDtoConverter usuarioPropietarioDtoConverter;


    @GetMapping("/")
    public ResponseEntity<List<GetUsuarioDto>> findAllPropietarios(@PageableDefault(size=10, page = 0) Pageable pageable) {
        List<Usuario> usuarios = usuarioService.findAllPropietarios(pageable);

        if (usuarios == null) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok()
                    .body(usuarios.stream()
                            .map(usuarioDtoConverter::converterUsuarioToUsuarioDto)
                            .collect(Collectors.toList()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUsuarioDto> findById(@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getRole().equals(UserRole.ADMIN) || usuario.getId().equals(id)) {
            if (usuarioService.findByIdPropietarios(id) == null)
                return ResponseEntity.notFound().build();
            else {
                return ResponseEntity.ok()
                        .body(usuarioDtoConverter.converterUsuarioToUsuarioDto(usuarioService.findByIdPropietarios(id)));
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getRole().equals(UserRole.ADMIN) || usuario.getId().equals(id)) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
   }
}
