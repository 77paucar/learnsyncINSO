package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.ClosedThreadException;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.ComentarioRepository;
import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import com.learnsyc.appweb.serializers.comentario.DeleteComentarioRequest;
import com.learnsyc.appweb.serializers.comentario.EditComentarioRequest;
import com.learnsyc.appweb.serializers.comentario.SaveComentarioRequest;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    HiloService hiloService;

    public List<Comentario> listarComentario(){return comentarioRepository.findAll();}

    public Comentario guardarComentario(Comentario comentario){
        if(comentario.getHilo().isCerrado()){
            throw new ClosedThreadException("No se admiten comentarios en el hilo #"+comentario.getHilo().getIdHilo());
        }
        return comentarioRepository.save(comentario);
    }

    public Comentario encontrarComentario(Long id){
        if(!comentarioRepository.existsById(id)){
            throw new ResourceNotExistsException("El comentario #"+id+" no existe");
        }
        return comentarioRepository.findByIdComentario(id);
    }

    private void guardarCambios(Comentario comentario){comentarioRepository.saveAndFlush(comentario);}

    public Comentario eliminarComentario(DeleteComentarioRequest request){ //En duda si se eliminara este metodo
        Comentario comentario = encontrarComentario(request.getId());
        comentarioRepository.deleteById(comentario.getIdComentario());
        return comentario;
    }

    public ComentarioSerializer editarComentario(EditComentarioRequest request){
        Comentario comentario = encontrarComentario(request.getId());
        comentario.setMensaje(request.getMensaje());
        comentario.setEsEditado(true);
        guardarCambios(comentario);
        return retornarComentario(comentario);
    }

    public ComentarioSerializer retornarComentario(Comentario comentario){
        return new ComentarioSerializer(comentario.getIdComentario(), comentario.getMensaje(), comentario.isEsEditado(), comentario.getFechaCreacion(), hiloService.retornarHilo(comentario.getHilo()),
                new UserSerializer(comentario.getUsuario().getUser(), comentario.getUsuario().getEmail()));
    }
}