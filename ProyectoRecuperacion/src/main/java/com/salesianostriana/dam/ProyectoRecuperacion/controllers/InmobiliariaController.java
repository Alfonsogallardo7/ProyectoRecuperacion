package com.salesianostriana.dam.ProyectoRecuperacion.controllers;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria.CreateInmobiliariaDto;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria.GetInmobiliariaDto;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.inmobiliaria.InmobiliariaDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda.GetViviendaImprescindibles;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Inmobiliaria;
import com.salesianostriana.dam.ProyectoRecuperacion.services.InmobiliariaService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.util.paginations.PaginationsLinksUtils;
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
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;
    private final PaginationsLinksUtils paginationsLinksUtils;

    @PostMapping("/")
    public ResponseEntity<GetInmobiliariaDto> addInmobiliaria (@RequestBody CreateInmobiliariaDto inmobiliariaDto) {
        Inmobiliaria inmobiliaria = inmobiliariaService.saveInmobiliaria(inmobiliariaDto);

        if (inmobiliariaService.findById(inmobiliaria.getId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        } else
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(inmobiliariaDtoConverter.convertInmobiliariaToGetInmobiliariaDto(inmobiliaria));
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<GetInmobiliariaDto> findById (@PathVariable UUID id) {
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id);

        if (inmobiliaria.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(inmobiliariaDtoConverter.convertInmobiliariaToGetInmobiliariaDto(inmobiliaria.get()));
    }
}
