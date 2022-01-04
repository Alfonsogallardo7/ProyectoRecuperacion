package com.salesianostriana.dam.ProyectoRecuperacion.users.models;

import com.salesianostriana.dam.ProyectoRecuperacion.models.Inmobiliaria;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Interesa;
import com.salesianostriana.dam.ProyectoRecuperacion.models.Vivienda;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@NamedEntityGraph(
        name = "grafoPropietarioVivienda",
        attributeNodes = {
                @NamedAttributeNode("listaVivienda")
        }
)
@Builder
@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario implements UserDetails, Serializable {

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

    private String apellidos;

    private String direccion;

    @NaturalId
    @Column(updatable = false,unique = true)
    private String email;

    private String telefono;

    private String avatar;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Builder.Default
    @OneToMany(mappedBy = "usuario")
    private List<Vivienda> listaVivienda = new ArrayList<>();

    @ManyToOne
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany
    private List<Interesa> listaInteresa = new ArrayList<>();

    /*
    ***** HELPERS *****
     */

    public void addToInmobiliaria(Inmobiliaria inmobiliaria) {
        this.inmobiliaria = inmobiliaria;
        inmobiliaria.getListaGestores().add(this);
    }

    public void removeFromInmobiliaria (Inmobiliaria inmobiliaria) {
        inmobiliaria.getListaGestores().remove(this);
        this.inmobiliaria = null;
    }

    /*
    ***** Metodos de UserDetails *****
    */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
