package com.salesianostriana.dam.ProyectoRecuperacion.security;

import com.salesianostriana.dam.ProyectoRecuperacion.security.dto.LoginDto;
import com.salesianostriana.dam.ProyectoRecuperacion.security.jwt.JwtProvider;
import com.salesianostriana.dam.ProyectoRecuperacion.security.jwt.JwtUserResponse;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager manager;

    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDto loginDto) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        convertUserToJwtUserResponse(usuario, jwt)
                );
    }

    @GetMapping("/me")
    public ResponseEntity<?> quienSoy (@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(convertUserToJwtUserResponse(usuario, null));
    }

    private JwtUserResponse convertUserToJwtUserResponse(Usuario usuario, String jwt) {
        return JwtUserResponse.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .avatar(usuario.getAvatar())
                .rol(usuario.getRole().name())
                .token(jwt).
                build();
    }
}
