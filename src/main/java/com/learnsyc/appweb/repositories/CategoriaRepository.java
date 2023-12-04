package com.learnsyc.appweb.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository; 
import com.learnsyc.appweb.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    Categoria findByNombre(String nombre);
    boolean existsCategoriaByNombre(String nombre);
}
