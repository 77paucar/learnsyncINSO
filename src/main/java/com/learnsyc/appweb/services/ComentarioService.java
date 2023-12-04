package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.repositories.ComentarioRepository;
import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private HiloService hiloService;
    @Autowired private UserService userService;

    public List<Comentario> listarComentario(){return comentarioRepository.findAll();}

    public Comentario guardarComentario(Comentario comentario){
        return comentarioRepository.save(comentario);
    }
    public ComentarioSerializer retornarComentario(Comentario comentario){
        return new ComentarioSerializer(comentario.getIdComentario(), comentario.getMensaje(), comentario.getFechaCreacion(), hiloService.retornarHilo(comentario.getHilo()),
                userService.retornarUsuario(comentario.getUsuario()));
    }
}