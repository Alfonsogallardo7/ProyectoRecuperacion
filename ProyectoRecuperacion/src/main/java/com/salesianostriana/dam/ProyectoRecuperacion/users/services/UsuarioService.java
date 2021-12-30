package com.salesianostriana.dam.ProyectoRecuperacion.users.services;

import com.salesianostriana.dam.ProyectoRecuperacion.services.base.BaseService;
import com.salesianostriana.dam.ProyectoRecuperacion.services.base.InmobiliariaService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.UserRole;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import com.salesianostriana.dam.ProyectoRecuperacion.users.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmobiliariaService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public List<Usuario> findAllPropietarios(Pageable pageable) {
        return repositorio.findAllByRole(UserRole.PROPIETARIO).get();
    }
}
