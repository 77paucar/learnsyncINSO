package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.repositories.ComentarioRepository;
import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import com.learnsyc.appweb.serializers.comentario.DeleteComentarioRequest;
import com.learnsyc.appweb.serializers.comentario.EditComentarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    HiloService hiloService;
    @Autowired UserService userService;

    public List<Comentario> listarComentario(){return comentarioRepository.findAll();}

    public Comentario guardarComentario(Comentario comentario){
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
                userService.retornarUsuario(comentario.getUsuario()));
    }
}