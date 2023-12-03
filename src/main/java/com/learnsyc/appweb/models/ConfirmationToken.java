package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name="confirmation_tokens")
@Entity
public class ConfirmationToken {
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_token") //Para que ubique a que columna agregar el valor
    private Long idToken;
    @Column(name="token")
    private String token;
    @Column(name="fecha_expiracion")
    private final LocalDateTime fechaExpiracion = LocalDateTime.now().plusMinutes(1);
    @Column(name="fecha_activacion")
    private LocalDateTime fechaActivacion;

    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName = "id_usuario")
    })

    @ManyToOne
    private Usuario usuario;

    public ConfirmationToken(Long idToken, String token, Usuario usuario){
        this.idToken = idToken;
        this.token = token;
        this.usuario = usuario;
    }
}
