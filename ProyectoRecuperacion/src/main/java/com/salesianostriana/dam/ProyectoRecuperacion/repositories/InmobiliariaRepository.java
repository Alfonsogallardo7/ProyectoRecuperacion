package com.salesianostriana.dam.ProyectoRecuperacion.repositories;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Inmobiliaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InmobiliariaRepository extends JpaRepository<Inmobiliaria, UUID> {
}
