package com.salesianostriana.dam.ProyectoRecuperacion.controllers;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria.CreateInmobiliariaDto;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria.GetInmobiliariaDto;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria.InmobiliariaDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Inmobiliaria;
import com.salesianostriana.dam.ProyectoRecuperacion.services.InmobiliariaService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.UsuarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.gestor.CreateGestorDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.UserRole;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.services.UsuarioService;
import com.salesianostriana.dam.ProyectoRecuperacion.util.paginations.PaginationsLinksUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inmobiliaria")
@CrossOrigin
@Tag(name = "Inmobiliaria", description = "El controlador de inmobiliarias")
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;
    private final PaginationsLinksUtils paginationsLinksUtils;
    private final UsuarioService usuarioService;
    private final UsuarioDtoConverter usuarioDtoConverter;

    @Operation(summary = "Agregar una nueva inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "201",
                    description = "La inmobiliaria se ha creado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Inmobiliaria.class))}),
            @ApiResponse (responseCode = "400",
                    description = "No se ha podido crear la inmobiliaria correctamente, error sintaxtico",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<GetInmobiliariaDto> addInmobiliaria (@RequestBody CreateInmobiliariaDto inmobiliariaDto) {
        Inmobiliaria inmobiliaria = inmobiliariaService.saveInmobiliaria(inmobiliariaDto);

        if (inmobiliariaService.findById(inmobiliaria.getId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(inmobiliariaDtoConverter.convertInmobiliariaToGetInmobiliariaDto(inmobiliaria));
    }

    @Operation(summary = "Listar todas la inmobiliarias existentes")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Se han listado todas las inmobiliarias correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Inmobiliaria.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ninguna inmobiliaria",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<Page<GetInmobiliariaDto>> findAll (@PageableDefault(size=10, page=0) Pageable pageable, HttpServletRequest request) {
        Page<Inmobiliaria> inmobiliarias = inmobiliariaService.findAll(pageable);

        if (inmobiliarias.isEmpty())
            return ResponseEntity.noContent().build();
        else {
            Page<GetInmobiliariaDto> result =
                    inmobiliarias.map(inmobiliariaDtoConverter::convertInmobiliariaToGetInmobiliariaDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity
                    .ok().header("Link" ,
                            paginationsLinksUtils.createLinkHeader(result , uriBuilder))
                    .body(result);
        }

    }

    @Operation(summary = "Listar todos los detalles de una inmobiliarias por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Se han mostrado todos los detalles de la inmobiliaria correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Inmobiliaria.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ninguna inmobiliaria",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetInmobiliariaDto> findById (@PathVariable UUID id) {
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);

        if (inmobiliaria.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(inmobiliariaDtoConverter.convertInmobiliariaToGetInmobiliariaDto(inmobiliaria.get()));
    }

    @Operation(summary = "Borrar una inmobiliaria por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "204",
                    description = "Se ha borrado la inmobiliaria correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Inmobiliaria.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ninguna inmobiliaria",
                    content = @Content),
            @ApiResponse (responseCode = "403",
            description = "Acceso denegado",
            content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInmobiliaria (@PathVariable UUID id) {
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);

        if (inmobiliaria.isEmpty())
            return ResponseEntity.notFound().build();
        else {
            inmobiliaria.get().getListaVivienda()
                    .forEach(vivienda -> vivienda.removeToInmobiliaria(inmobiliaria.get()));
            inmobiliariaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Añadir un gestor de una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "201",
                    description = "El gestor se ha creado y añiadido correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Inmobiliaria.class))}),
            @ApiResponse (responseCode = "400",
                    description = "No se ha podido crear el gestor de la inmobiliaria, error sintaxtico",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @PostMapping("/{id}/gestor")
    public ResponseEntity<GetInmobiliariaDto> addGestor (@PathVariable UUID id, @AuthenticationPrincipal Usuario user, @RequestBody CreateGestorDto newGestor) {
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);
        Usuario gestor = usuarioService.findGestorById(user.getId());
        if (inmobiliaria.isEmpty())
            return ResponseEntity.notFound().build();

        else if (gestor != null || user.getRole().equals(UserRole.ADMIN) && inmobiliaria.isPresent()) {
            Usuario usuarioGuardado = usuarioService.saveGestor(newGestor);
            usuarioGuardado.addToInmobiliaria(inmobiliaria.get());
            usuarioService.edit(usuarioGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(inmobiliariaDtoConverter.convertInmobiliariaToGetInmobiliariaDto(inmobiliaria.get()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Operation(summary = "Listar todos los gestores de una inmobiliarias por el id de la inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "200",
                    description = "Se han listado todos los gestores de la inmobiliaria correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Inmobiliaria.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ninguna inmobiliaria",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })
    @GetMapping("/{id}/gestor")
    public ResponseEntity<List<GetUsuarioDto>> findAllGestor(@PathVariable UUID id,  @AuthenticationPrincipal Usuario user) {
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);

        if (inmobiliaria.isEmpty())
            return ResponseEntity.notFound().build();
        else if (user.getRole().equals(UserRole.ADMIN) || user.getInmobiliaria().getId().equals(inmobiliaria.get().getId())) {
            return ResponseEntity.ok(inmobiliaria.get().getListaGestores()
                    .stream().map(usuarioDtoConverter::converterUsuarioToUsuarioDto).collect(Collectors.toList()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Operation(summary = "Borrar un gestor de una inmobiliaria por su id")
    @ApiResponses(value = {
            @ApiResponse (responseCode = "204",
                    description = "Se ha borrado el gestor de la inmobiliaria correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema (implementation = Inmobiliaria.class))}),
            @ApiResponse (responseCode = "404",
                    description = "No se ha podido encontrar ninguna gestor de la inmobiliaria",
                    content = @Content),
            @ApiResponse (responseCode = "403",
                    description = "Acceso denegado",
                    content = @Content)
    })

    @DeleteMapping("/gestor/{id}")
    public ResponseEntity<?> deleteGestor (@PathVariable UUID id, @AuthenticationPrincipal Usuario user) {
        Usuario gestorLogueado = usuarioService.findGestorById(user.getId());
        Usuario gestor = usuarioService.findGestorById(id);

        if (gestor == null)
            return ResponseEntity.notFound().build();

        else if (user.getRole().equals(UserRole.ADMIN) || gestor.getInmobiliaria().getId().equals(gestorLogueado.getInmobiliaria().getId())) {

            gestor.removeFromInmobiliaria(gestor.getInmobiliaria());
            usuarioService.delete(gestor);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }
}
