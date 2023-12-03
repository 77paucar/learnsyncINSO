package com.learnsyc.appweb.models;

import java.time.*; //Para las fechas
import java.util.Collection;
import java.util.List;

import com.learnsyc.appweb.serializers.usuario.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data //Construye métodos Set, Get, toString
@Table(name="usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario", "email"})}) //Da a entender que sera un modelo de base de datos
@Entity //Da a entender que sera una entidad de base de datos
@NoArgsConstructor //Genera constructor vacio
public class Usuario implements UserDetails {
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO) //Hace un autoincrement
    @Column(name="id_usuario") //Para que ubique a que columna agregar el valor
    Long idUsuario;
    @Column(name="usuario", nullable = false)
    String user;
    @Column(name="password", nullable = false)
    String password;
    @Column(name="email", nullable = false)
    String email;
    @Enumerated(EnumType.STRING)
    Role role;
    @Column(name="fecha_creacion", nullable = false)
    final LocalDate fechaCreacion = LocalDate.now();
    @Column(name="enable")
    boolean enable;
    @Column(name="fecha_expiracion")
    LocalDateTime fechaExpiracion;

    public Usuario(Long idUsuario, String user, String password, String email){
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
        this.email = email;
        role = Role.STUDENT;
        enable = false;
        fechaExpiracion = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return user;
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