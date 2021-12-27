package com.salesianostriana.dam.ProyectoRecuperacion.repositories;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViviendaRepository extends JpaRepository<Vivienda, UUID> {
}
