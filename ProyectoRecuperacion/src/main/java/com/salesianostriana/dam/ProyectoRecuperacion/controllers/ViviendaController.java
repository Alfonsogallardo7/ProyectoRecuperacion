package com.salesianostriana.dam.ProyectoRecuperacion.controllers;

import com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa.CreateInteresDto;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa.CreateInteresaInmprescindibleDto;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa.GetInteresadoDto;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.interesa.InteresaDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.dto.vivienda.*;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Inmobiliaria;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import com.salesianostriana.dam.ProyectoRecuperacion.services.InmobiliariaService;
import com.salesianostriana.dam.ProyectoRecuperacion.services.ViviendaService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.UserRole;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.services.InteresadoService;
import com.salesianostriana.dam.ProyectoRecuperacion.util.paginations.PaginationsLinksUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vivienda")
@RequiredArgsConstructor
@CrossOrigin
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final InmobiliariaService inmobiliariaService;
    private final ViviendaDtoConverter viviendaDtoConverter;
    private final ViviendaImprescindibleDtoConverter viviendaImprescindibleDtoConverter;
    private final InteresadoService interesadoService;
    private final InteresaDtoConverter interesaDtoConverter;
    private final PaginationsLinksUtils paginationsLinksUtils;

        @GetMapping("/")
        public ResponseEntity<Page<GetViviendaImprescindibles>> findAll(@PageableDefault(size=10, page=0) Pageable pageable, HttpServletRequest request) {
            Page<Vivienda> data = viviendaService.findAll(pageable);
            if (data.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                Page<GetViviendaImprescindibles> result =
                        data.map(viviendaImprescindibleDtoConverter::convertViviendaToViviendaImprescindibleDto);

                UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

                return ResponseEntity
                        .ok().header("Link" ,
                                paginationsLinksUtils.createLinkHeader(result , uriBuilder))
                        .body(result);
            }
        }

        @GetMapping("/{id}")
        public ResponseEntity<GetViviendaDto> findById (@PathVariable UUID id) {
            Optional<Vivienda> viviendaBuscada = viviendaService.findById(id);

            if (viviendaBuscada.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else
                return ResponseEntity.ok().body(viviendaDtoConverter
                        .convertViviendaToViviendaDto(viviendaBuscada.get()));

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete (@PathVariable UUID id, @AuthenticationPrincipal Usuario user) {
            Optional<Vivienda> viviendaBuscada = viviendaService.findById(id);

            if (viviendaBuscada.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else if (user.getRole().equals(UserRole.ADMIN) || (user.getId().equals(viviendaBuscada.get().getUsuario().getId()))) {
                viviendaService.deleteById(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


        @PostMapping("/")
        public ResponseEntity<GetViviendaDto> add (@RequestBody CreateViviendaDto viviendaNueva, @AuthenticationPrincipal Usuario usuario) {
            Vivienda vivienda = viviendaService.saveViviendaConPropietario(viviendaNueva,usuario);

            if (vivienda == null) {
                return ResponseEntity.badRequest().build();
            } else
                return ResponseEntity.status(HttpStatus.CREATED).body(viviendaDtoConverter.convertViviendaToViviendaDto(vivienda));
        }

        @PutMapping("/{id}")
        public ResponseEntity<GetViviendaDto> edit (@PathVariable UUID id, @AuthenticationPrincipal Usuario user, @RequestBody CreateViviendaDto vivienda) {
            Optional<Vivienda> viviendaBuscada = viviendaService.findById(id);

            if (viviendaBuscada.isEmpty()){
                return ResponseEntity.notFound().build();
            } else if (user.getRole().equals(UserRole.ADMIN) || (viviendaBuscada.get().getUsuario().getId().equals(user.getId()))) {
                GetViviendaDto viviendaDto = GetViviendaDto.builder()
                        .titulo(vivienda.getTitulo())
                        .tipo(vivienda.getTipo())
                        .avatar(vivienda.getAvatar())
                        .codigoPostal(vivienda.getCodigoPostal())
                        .precio(vivienda.getPrecio())
                        .metrosCuadrados(vivienda.getMetrosCuadrados())
                        .descripcion(vivienda.getDescripcion())
                        .lating(vivienda.getLating())
                        .direccion(vivienda.getDireccion())
                        .poblacion(vivienda.getPoblacion())
                        .provincia(vivienda.getProvincia())
                        .numHabitacion(vivienda.getNumHabitacion())
                        .numBanios(vivienda.getNumBanios())
                        .piscina(vivienda.isPiscina())
                        .ascensor(vivienda.isAscensor())
                        .garaje(vivienda.isGaraje())
                        .nombrePropietario(viviendaBuscada.get().getUsuario().getNombre())
                        .emailPropietario(viviendaBuscada.get().getUsuario().getEmail())
                        .telefonoPropietario(viviendaBuscada.get().getUsuario().getTelefono())
                        .build();

                viviendaService.edit(viviendaDtoConverter.createViviendaDtoToVivienda(vivienda, viviendaBuscada.get()));
                return ResponseEntity.status(HttpStatus.CREATED).body(viviendaDto);
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        @PostMapping("/{id}/inmobiliaria/{id2}")
        public ResponseEntity<GetViviendaDto> addGestionInmobiliaria(@PathVariable UUID id,
                                                                     @PathVariable UUID id2, @AuthenticationPrincipal Usuario user) {
            Optional <Vivienda> vivienda = viviendaService.findById(id);
            Optional <Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id2);

            if (vivienda.isEmpty() || inmobiliaria.isEmpty())
                return ResponseEntity.notFound().build();
            else if (user.getRole().equals(UserRole.ADMIN) || vivienda.get().getUsuario().getId().equals(user.getId())){
                Vivienda vivienda1 = viviendaService.addInmobiliaria(vivienda.get(), inmobiliaria.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(viviendaDtoConverter.convertViviendaToViviendaDto(vivienda1));
            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        @DeleteMapping("/{id}/inmobiliaria/{id2}")
        public ResponseEntity<GetViviendaDto> deleteGestionInmobiliaria (@PathVariable UUID id, @PathVariable UUID id2,
                                                                         @AuthenticationPrincipal Usuario user) {
            Optional<Vivienda> vivienda = viviendaService.findById(id);
            Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id2);

            if ((vivienda.isEmpty()) || (inmobiliaria.isEmpty()))
                return ResponseEntity.notFound().build();

            if (user.getRole().equals(UserRole.ADMIN) || (vivienda.get().getUsuario().getId().equals(user.getId()))) {
                Vivienda vivienda1 = viviendaService.deleteInmobiliaria(vivienda.get(), inmobiliaria.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(viviendaDtoConverter.convertViviendaToViviendaDto(vivienda1));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


        @PostMapping("/{id}/meinteresa")
        public ResponseEntity<GetInteresadoDto> addInteresado (@PathVariable UUID id, @RequestBody CreateInteresaInmprescindibleDto mensaje, @AuthenticationPrincipal Usuario user
                                                                    , CreateInteresDto createInteresDto) {
           Optional<Vivienda> vivienda = viviendaService.findById(id);

           if (vivienda.isEmpty())
               return ResponseEntity.notFound().build();
           else
               return ResponseEntity.status(HttpStatus.CREATED).body(
                       interesaDtoConverter.convertInteresaToInteresaDto(interesadoService.saveInteresado(createInteresDto,mensaje,user,vivienda.get()))
               );

        }

        @DeleteMapping("/{id}/meinteresa")
        public ResponseEntity<?> deleteInteresado (@PathVariable UUID id, @AuthenticationPrincipal Usuario user) {
            Optional<Vivienda> vivienda = viviendaService.findById(id);

            if (vivienda.isEmpty())
                return ResponseEntity.notFound().build();
            else if (user.getRole().equals(UserRole.ADMIN)) {
                vivienda.get().getListaInteresa().clear();
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
}
