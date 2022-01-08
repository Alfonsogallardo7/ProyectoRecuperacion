package com.salesianostriana.dam.ProyectoRecuperacion.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAccesDeniedHandler implements AccessDeniedHandler {


    private final ObjectMapper mapper;


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType("application/json");

        Map<String, String> mensajes =
                Map.of("mensaje", e.getMessage());

        String strjson = mapper.writeValueAsString(mensajes);

        httpServletResponse.getWriter().println(strjson);
    }

/*
    private final ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        Map<String, String> mensajes = Map.of("mensaje", accessDeniedException.getMessage());

        String stringJson = mapper.writeValueAsString(mensajes);

        response.getWriter().println(stringJson);
    }*/
}
