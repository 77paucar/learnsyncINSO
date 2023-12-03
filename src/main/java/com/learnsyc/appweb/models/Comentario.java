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
    Long idComentario;
    @Column(name="mensaje")
    String mensaje;
    @Column(name="fecha_creacion")
    final LocalDate fechaCreacion = LocalDate.now(); //Cambiar a LocalDateTime
    @Column(name="es_editado")
    boolean esEditado;
    @Lob
    @Column(name = "archivo") //Si es criterio de aceptacion se queda
    byte[] archivo;
    @JoinColumns({
            @JoinColumn(name="id_hilo", referencedColumnName="id_hilo")
    })
    @ManyToOne
    Hilo hilo;

    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuario;

    public Comentario(Long idComentario, String mensaje, Hilo hilo, Usuario usuario, byte[] archivo){
        this.idComentario = idComentario;
        this.mensaje = mensaje;
        esEditado = false;
        this.hilo = hilo;
        this.usuario = usuario;
        this.archivo = archivo;
    }
}
