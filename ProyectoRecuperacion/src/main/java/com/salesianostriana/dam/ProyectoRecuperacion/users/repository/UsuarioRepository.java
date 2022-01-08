package com.salesianostriana.dam.ProyectoRecuperacion.users.repository;

import com.salesianostriana.dam.ProyectoRecuperacion.users.models.UserRole;
import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional <List<Usuario>> findAllByRole(UserRole userRole);

    Optional <Usuario> findByEmail(String email);
}
