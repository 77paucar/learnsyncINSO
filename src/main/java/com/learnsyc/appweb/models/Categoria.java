package com.learnsyc.appweb.models;

import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor; 
import lombok.AllArgsConstructor; 
@Data
@Table(name="categorias") //Da a entender que sera un modelo de base de datos
@Entity //Da a entender que sera una entidad de base de datos
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor
public class Categoria {
    @Id //Identifica a la primary key
    @GeneratedValue(strategy= GenerationType.AUTO) //Hace un autoincrement
    //Column hibernate name strategy googlear despues
    @Column(name="id_categorias") //Para que ubique a que columna agregar el valor
    private Long idCategorias;
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
}