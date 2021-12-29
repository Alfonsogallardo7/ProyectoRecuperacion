package com.salesianostriana.dam.ProyectoRecuperacion.models;

import com.salesianostriana.dam.ProyectoRecuperacion.users.models.Usuario;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "grafoInteresaUsuario",
        attributeNodes = {
                @NamedAttributeNode("usuario")
        }
)
@Builder
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Interesa implements Serializable {

    @Builder.Default
    @EmbeddedId
    private InteresaPK id = new InteresaPK();

    @ManyToOne
    @MapsId("vivienda_id")
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("usuario_id")
    private Usuario usuario;

    @CreatedDate
    private LocalDateTime createdDate;

    @Lob
    private String mensaje;

    /*
    ***** HELPERS *****
     */

    public void addToVivienda (Vivienda vivienda) {
        this.vivienda = vivienda;
        vivienda.getListaInteresa().add(this);
    }

    public void removeToVivienda (Vivienda vivienda) {
        this.vivienda = null;
        vivienda.getListaInteresa().remove(this);
    }

    public void addToUsuario (Usuario usuario) {
        this.usuario = usuario;
        usuario.getListaInteresa().add(this);
    }

    public void removeToUsuario (Usuario usuario) {
        this.usuario = null;
        usuario.getListaInteresa().remove(this);
    }

    public void addViviendaToUsuario (Vivienda vivienda, Usuario usuario) {
        addToVivienda(vivienda);
        addToUsuario(usuario);
    }

    public void removeViviedaToUsuario (Vivienda vivienda, Usuario usuario) {
        removeToUsuario(usuario);
        removeToVivienda(vivienda);
    }

}
