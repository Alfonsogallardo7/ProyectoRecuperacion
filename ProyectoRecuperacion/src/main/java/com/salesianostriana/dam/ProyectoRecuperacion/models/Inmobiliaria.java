package com.salesianostriana.dam.ProyectoRecuperacion.models;

import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Inmobiliaria implements Serializable {

    @GeneratedValue(generator = "UUID")
    @Id
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )}
    )
    private UUID id;

    private String nombre;

    private String email;

    private String telefono;

    @Builder.Default
    @OneToMany
    private List<Vivienda> listaVivienda = new ArrayList<>();

    @Builder.Default
    @OneToMany
    private List<Usuario> listaGestores = new ArrayList<>();
}
