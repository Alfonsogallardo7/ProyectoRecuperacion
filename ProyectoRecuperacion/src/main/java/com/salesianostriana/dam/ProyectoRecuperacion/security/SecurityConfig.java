package com.salesianostriana.dam.ProyectoRecuperacion.security;

import com.salesianostriana.dam.ProyectoRecuperacion.security.jwt.JwtAccesDeniedHandler;
import com.salesianostriana.dam.ProyectoRecuperacion.security.jwt.JwtAuthenticationEntryPoint;
import com.salesianostriana.dam.ProyectoRecuperacion.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthorizationFilter filter;
    private final JwtAccesDeniedHandler accessDeniedHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/register/user", "/auth/login").anonymous()
                .antMatchers(HttpMethod.POST, "/auth/register/gestor", "inmobiliaria").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/auth/register/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/propietario/").authenticated()
                .antMatchers(HttpMethod.GET, "/propietario/{id}").hasAnyRole("ADMIN", "PROPIETARIO")
                .antMatchers(HttpMethod.DELETE, "/propietario/{id}").hasAnyRole("ADMIN", "PROPIETARIO")
                .antMatchers(HttpMethod.POST, "/vivienda/").hasRole("PROPIETARIO")
                .antMatchers(HttpMethod.GET, "/vivienda/").authenticated()
                .antMatchers(HttpMethod.GET, "/vivienda/{id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/vivienda/{id}").hasAnyRole("PROPIETARIO", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/vivienda/{id}").hasAnyRole("PROPIETARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/vivienda/{id}/inmobiliaria/{id}").hasAnyRole("PROPIETARIO", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/vivienda/{id}/inmobiliaria").hasAnyRole("ADMIN", "PROPIETARIO", "GESTOR")
                .antMatchers(HttpMethod.POST, "/inmobiliaria/{id}/gestor").hasAnyAuthority("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/gestor/{id}").hasAnyRole("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.GET, "/inmobiliaria/gestor").hasAnyRole("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.GET, "/inmobiliaria/").authenticated()
                .antMatchers(HttpMethod.GET, "/inmobiliaria/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/vivienda/{id}/meinteresa").hasRole("PROPIETARIO")
                .antMatchers(HttpMethod.DELETE, "/vivienda/{id}/meinteresa").hasAnyRole("ADMIN", "PROPIETARIO")
                .antMatchers(HttpMethod.GET, "/interesado/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/interesado/{id}").hasAnyRole("ADMIN", "PROPIETARIO")
                .antMatchers(HttpMethod.GET, "/vivienda/top?n=10").hasRole("USER")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/


    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccesDeniedHandler jwtAccesDeniedHandler;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccesDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/login", "/auth/register/user").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register/gestor", "/auth/register/admin", "/inmobiliaria/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/propietario/", "/vivienda/","vivienda/interesado", "/vivienda/{id}" ,"/inmobiliaria/", "/inmobiliaria/{id}", "/vivienda/top?n=10").authenticated()
                .antMatchers(HttpMethod.GET, "/propietario/{id}", "/interesado/{id}").hasAnyRole("ADMIN", "PROPIETARIO")
                .antMatchers(HttpMethod.DELETE, "/propietario/{id}", "/vivienda/{id}", "/vivienda/{id}/inmobiliaria", "/vivienda/{id}/meinteresa").hasAnyRole("ADMIN", "PROPIETARIO")
                .antMatchers(HttpMethod.POST, "/vivienda/", "/vivienda/{id}/meinteresa").hasRole("PROPIETARIO")
                .antMatchers(HttpMethod.PUT, "/vivienda/{id}").hasAnyRole("PROPIETARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/vivienda/{id}/inmobiliaria/{id2}").hasAnyRole("PROPIETARIO", "ADMIN")
                .antMatchers(HttpMethod.POST, "/inmobiliaria/{id}/gestor").hasAnyRole("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/gestor/{id}").hasAnyRole("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.GET, "/inmobiliaria/{id}/gestor").hasAnyRole("ADMIN", "GESTOR")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/interesado/").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
