package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*; //Para las fechas

@Data
@Table(name="comentarios")
@Entity
@NoArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_comentario")
    private Long idComentario;
    @Column(name="mensaje", nullable = false)
    private String mensaje;
    @Column(name="fecha_creacion", nullable = false)
    private final LocalDate fechaCreacion = LocalDate.now(); //Cambiar a LocalDateTime
    @JoinColumns({
            @JoinColumn(name="id_hilo", referencedColumnName="id_hilo", nullable = false)
    })
    @ManyToOne
    private Hilo hilo;

    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario", nullable = false)
    })
    @ManyToOne
    private Usuario usuario;

    public Comentario(Long idComentario, String mensaje, Hilo hilo, Usuario usuario){
        this.idComentario = idComentario;
        this.mensaje = mensaje;
        this.hilo = hilo;
        this.usuario = usuario;
    }
}
