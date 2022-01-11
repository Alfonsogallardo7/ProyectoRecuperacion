package com.salesianostriana.dam.ProyectoRecuperacion.users.controller;

import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.UsuarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.propietario.UsuarioPropietarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.UserRole;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "propietario", description = "Los controladores del usuario propietario")
public class UsuarioPropietarioController {

    private final UsuarioService usuarioService;
    private final UsuarioDtoConverter usuarioDtoConverter;
    private final UsuarioPropietarioDtoConverter usuarioPropietarioDtoConverter;

    @Operation(summary = "Listar todos los propietarios existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                description = "Se han listado todos los propietarios correctamente",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                description = "No se han encontrado ningun propietario",
                content = @Content)
    })
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

    @Operation(summary = "Listar los detalles del propietario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han mostrado todos los detalles del usuario propietario correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado ningun propietario",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                description = "Acceso denegado",
                content = @Content)
    })
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

    @Operation(summary = "Se elimina el propietario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha borrado el propietario correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado ningun propietario",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal Usuario usuario) {
        if (usuario.getRole().equals(UserRole.ADMIN) || usuario.getId().equals(id)) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
   }
}
