package com.salesianostriana.dam.ProyectoRecuperacion.repositories;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ViviendaRepository extends JpaRepository<Vivienda, UUID> {

}
