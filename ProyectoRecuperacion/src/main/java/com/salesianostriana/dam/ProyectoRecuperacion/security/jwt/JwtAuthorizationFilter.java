package com.salesianostriana.dam.ProyectoRecuperacion.security.jwt;

import com.salesianostriana.dam.ProyectoRecuperacion.users.services.UsuarioService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UsuarioService service;
    private final JwtProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getJwtFromRequest(request);

        try {
            if (StringUtils.hasText(token)&&provider.validateToken(token)) {
                UUID idUsuario = provider.getUserIdFromJwt(token);
                Optional<Usuario> usuarioOptional = service.findById(idUsuario);
                if (usuarioOptional.isPresent()){
                    Usuario usuario = usuarioOptional.get();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            usuario,
                            usuario.getRol(),
                            usuario.getAuthorities()
                    );

                    authenticationToken.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }catch (Exception exception) {
            log.info("No se ha podido establecer el contexto de seguridad (" +exception.getMessage() + ")");
        }

        filterChain.doFilter(request, response);

    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtProvider.TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken)&&bearerToken.startsWith(JwtProvider.TOKE_PREFIX)){
            return bearerToken.substring(JwtProvider.TOKE_PREFIX.length());
        }
        return null;
    }
}
