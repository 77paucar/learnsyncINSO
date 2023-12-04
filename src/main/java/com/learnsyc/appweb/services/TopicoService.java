package com.learnsyc.appweb.services;

import java.util.List;

import com.learnsyc.appweb.excepciones.ResourceAlreadyExistsException;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.serializers.topico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.repositories.TopicoRepository;

@Service
public class TopicoService {
    
    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> listarTopico(){
        return topicoRepository.findAll();
    }

    public List<Topico> listarTopicoPorCategoria(Categoria categoria){
        return topicoRepository.findAllByCategoria(categoria);
    }

    public Topico guardarTopico(Topico topico){
        if(topicoRepository.existsTopicoByNombre(topico.getNombre())){
            throw new ResourceAlreadyExistsException("El tópico "+topico.getNombre()+" existe");
        }
        return topicoRepository.save(topico);
    }

    public Topico buscarTopico(String nombre){ //Devuelve lo que se mostrara en la página
        if(!topicoRepository.existsTopicoByNombre(nombre)) {
            throw new ResourceNotExistsException("El tópico " + nombre + " no existe");
        }
        return topicoRepository.findByNombre(nombre);
    }

    public TopicoSerializer retornarTopico(Topico topico){
        return new TopicoSerializer(topico.getIdTopico(), topico.getNombre(), topico.getDescripcion(),
                new CategoriaSerializer(topico.getCategoria().getNombre(), topico.getCategoria().getDescripcion()));
    }

    public Topico encontrarTopico(Long request) {
        return topicoRepository.findByIdTopico(request);
    }

}