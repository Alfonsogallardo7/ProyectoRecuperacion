package com.salesianostriana.dam.ProyectoRecuperacion.users.controller;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import com.salesianostriana.dam.ProyectoRecuperacion.services.ViviendaService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.GetUsuarioDto;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.UsuarioDtoConverter;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.services.UsuarioService;
import com.salesianostriana.dam.ProyectoRecuperacion.util.paginations.PaginationsLinksUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interesado")
public class UsuarioInteresadoController {

    private final UsuarioDtoConverter usuarioDtoConverter;
    private final ViviendaService viviendaService;
    private final UsuarioService usuarioService;
    private final PaginationsLinksUtils paginationsLinksUtils;

    /*@GetMapping("/")
    public ResponseEntity<List<GetUsuarioDto>> findAll (@PageableDefault(size=10, page = 0) Pageable pageable) {
        Page<Vivienda> viviendas = viviendaService.findAll(pageable);
        List<Usuario> usuarios = usuarioService.findAllInteresados();

        if (viviendas.isEmpty())
            return ResponseEntity.noContent().build();
        else if (usuarios == null) {
            return ResponseEntity.ok(
                    usuarios.stream().map(usuarioDtoConverter::converterUsuarioToUsuarioDto).collect(Collectors.toList()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
*/

}
