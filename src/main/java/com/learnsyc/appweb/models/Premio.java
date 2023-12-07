package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "premios")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_premio")
    private Long idPremio;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    int precio;

    @Column(name = "imagen", columnDefinition = "text", nullable = false)
    String imagen;

    @JoinColumns({
            @JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
    })
    @ManyToOne
    Usuario usuario;
}