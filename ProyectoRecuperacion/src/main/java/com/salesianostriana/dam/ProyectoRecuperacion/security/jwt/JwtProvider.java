package com.salesianostriana.dam.ProyectoRecuperacion.security.jwt;

import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Value;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Log
@Service
public class JwtProvider {

    public static final String TOKEN_TYPE = "JWT";

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKE_PREFIX = "Bearer ";

    @Value("${jwt.secret:llevalatararaunvestidoblancollenodecascabeles}")
    private String jwtSecret;

    @Value("${jwt.duration:86400}")
    private int jwtLifeInSeconds;

    private JwtParser jwtParser;

    @PostConstruct
    public void init () {
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build();
    }

    public String generateToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Date tokenExpDate = Date
                .from(LocalDateTime
                        .now()
                        .plusSeconds(jwtLifeInSeconds)
                        .atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(usuario.getId().toString())
                .setIssuedAt(tokenExpDate)
                .claim("nombre",usuario.getRol().name())
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public boolean validateToken(String token) {
        try{
            jwtParser.parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException
        | IllegalArgumentException exception) {
            log.info("Error con el token " + exception.getMessage());
        }
        return false;
    }

    public UUID getUserIdFromJwt(String token) {
        return UUID.fromString(jwtParser.parseClaimsJwt(token).getBody().getSubject());
    }
}
