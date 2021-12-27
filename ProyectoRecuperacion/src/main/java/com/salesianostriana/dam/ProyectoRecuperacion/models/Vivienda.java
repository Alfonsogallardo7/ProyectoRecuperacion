package com.salesianostriana.dam.ProyectoRecuperacion.models;

import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Vivienda implements Serializable {

    @GeneratedValue(generator = "UUID")
    @Id
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {@Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )}
    )
    private UUID id;

    private String titulo;

    @Lob
    private String descripcion;

    private String avatar;

    private String lating;

    private String direccion;

    private String codigoPostal;

    private String poblacion;

    private String provincia;

    private String tipo;

    private double precio;

    private int numHabitaciones;

    private double metrosCuadrados;

    private int numBanios;

    private boolean piscina;

    private boolean ascensor;

    private boolean garaje;

    @ManyToOne
    private Inmobiliaria inmobiliaria;

    @ManyToOne
    private Usuario usuario;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.REMOVE)
    private List<Interesa> listaInteresa = new ArrayList<>();

    /*
    ***** HELPERS *****
     */

    public void addToInmobiliaria (Inmobiliaria inmobiliaria) {
        this.inmobiliaria = inmobiliaria;
        inmobiliaria.getListaVivienda().add(this);
    }

    public void removeToInmobiliaria (Inmobiliaria inmobiliaria) {
        this.inmobiliaria = null;
        inmobiliaria.getListaVivienda().remove(this);
    }

}
