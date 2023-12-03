package com.learnsyc.appweb.models;

import java.time.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import com.learnsyc.appweb.models.Categoria;
@Data
@Table(name="topicos", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Topico {
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO) //Hace un autoincrement
    @Column(name="id_topico") //Para que ubique a que columna agregar el valor
    private Long idTopico;
    @Column(name="nombre", nullable = false)
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="fecha_creacion", nullable = false)
    private final LocalDate fechaCreacion = LocalDate.now();
    //Cambiar a guion bajo los name
    @JoinColumns({
        @JoinColumn(name="id_categorias", referencedColumnName="id_categorias", nullable = false)
    })
    @ManyToOne
    private Categoria categoria;
}
