package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name ="mazos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mazo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mazo")
    private Long idMazo;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "imagen", nullable = false, columnDefinition = "text")
    private String imagen;
    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    private Usuario usuario;
}
