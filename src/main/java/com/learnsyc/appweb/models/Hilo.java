package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*; //Para las fechas
@Data
@Table(name="hilos")
@Entity
@NoArgsConstructor
public class Hilo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_hilo")
    private Long idHilo;
    @Column(name="titulo", nullable = false)
    private String titulo;
    @Column(name="mensaje")
    private String mensaje;
    @Column(name="fecha_creacion", nullable = false)
    private final LocalDate fechaCreacion = LocalDate.now(); //Cambiar a LocalDateTime
    @JoinColumns({
            @JoinColumn(name="id_topico", referencedColumnName="id_topico", nullable = false)
    })
    @ManyToOne
    private Topico topico;

    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario", nullable = false)
    })
    @ManyToOne
    private Usuario usuario;
    public Hilo(Long idHilo, String titulo, String mensaje, Topico topico, Usuario usuario){
        this.idHilo = idHilo;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.topico = topico;
        this.usuario = usuario;
    }
}
