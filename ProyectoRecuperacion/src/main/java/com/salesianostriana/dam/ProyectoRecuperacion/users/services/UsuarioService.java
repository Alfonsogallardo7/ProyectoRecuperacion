package com.salesianostriana.dam.ProyectoRecuperacion.users.services;

import com.salesianostriana.dam.ProyectoRecuperacion.services.base.BaseService;
import com.salesianostriana.dam.ProyectoRecuperacion.services.InmobiliariaService;
import com.salesianostriana.dam.ProyectoRecuperacion.users.dto.CreateUsuarioDto;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, UUID, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmobiliariaService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + "no encontrado"));
    }

    public List<Usuario> findAllPropietarios(Pageable pageable) {
        if(repositorio.findAllByRole(UserRole.PROPIETARIO).isEmpty()){
            return null;
        }else {
            return repositorio.findAllByRole(UserRole.PROPIETARIO).get();
        }
    }

    public Usuario findByIdPropietarios(UUID id) {
        Optional<Usuario> usuario = findById(id);

        if (usuario.isEmpty()){
            return null;
        }else if (usuario.get().getRole().equals(UserRole.PROPIETARIO)) {
            return usuario.get();
        }
        return null;
    }

    public Usuario savePropietario (CreateUsuarioDto nuevoUsuario) {
        if (nuevoUsuario.getPassword().contentEquals(nuevoUsuario.getPassword2())){
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoUsuario.getPassword()))
                    .nombre(nuevoUsuario.getNombre())
                    .apellidos(nuevoUsuario.getApellidos())
                    .avatar(nuevoUsuario.getAvatar())
                    .email(nuevoUsuario.getEmail())
                    .telefono(nuevoUsuario.getTelefono())
                    .direccion(nuevoUsuario.getDireccion())
                    .role(UserRole.PROPIETARIO)
                    .build();
            return save(usuario);
        }

        return null;
    }

}
