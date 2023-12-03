package com.learnsyc.appweb.services;

import java.util.List;

import com.learnsyc.appweb.excepciones.ResourceAlreadyExistsException;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria guardarCategoria(Categoria categoria){
        if(categoriaRepository.existsCategoriaByNombre(categoria.getNombre())){
            throw new ResourceAlreadyExistsException("La categoría "+ categoria.getNombre()+" existe");
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria encontrarCategoria(String nombre){
        if(!categoriaRepository.existsCategoriaByNombre(nombre)) {
            throw new ResourceNotExistsException("La categoria "+nombre+" no existe");
        }return categoriaRepository.findByNombre(nombre);
    }
}