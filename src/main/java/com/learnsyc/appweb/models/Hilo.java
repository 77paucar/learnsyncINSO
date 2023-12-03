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
    Long idHilo;
    @Column(name="titulo")
    String titulo;
    @Column(name="mensaje")
    String mensaje;
    @Column(name="cerrado")
    boolean cerrado; //Me parece que ya no va
    @Column(name="fecha_creacion")
    final LocalDate fechaCreacion = LocalDate.now(); //Cambiar a LocalDateTime
    @Lob
    @Column(name = "archivo")
    byte[] archivo; //Si no es criterio de aceptacion se va

    @JoinColumns({
            @JoinColumn(name="id_topico", referencedColumnName="id_topico")
    })
    @ManyToOne
    Topico topico;

    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuario;
    public Hilo(Long idHilo, String titulo, String mensaje, Topico topico, Usuario usuario, byte[] archivo){
        this.idHilo = idHilo;
        this.titulo = titulo;
        this.mensaje = mensaje;
        cerrado = false; //Posiblemente se va
        this.topico = topico;
        this.usuario = usuario;
        this.archivo = archivo;
    }
}
