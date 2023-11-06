package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import com.learnsyc.appweb.serializers.comentario.SaveComentarioRequest;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.topico.TopicoSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import com.learnsyc.appweb.services.ComentarioService;
import com.learnsyc.appweb.services.HiloService;
import com.learnsyc.appweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comentario")
//@CrossOrigin(origins = "http://localhost:4200")
public class ComentarioController {
    @Autowired UserService userService;
    @Autowired HiloService hiloService;
    @Autowired ComentarioService comentarioService;

    @GetMapping("/")
    public List<ComentarioSerializer> listarComentario(){
        return comentarioService.listarComentario().stream().map((it) -> new ComentarioSerializer(it.getMensaje(),
                new HiloSerializer(it.getHilo().getTitulo(), it.getHilo().getMensaje(),
                        new TopicoSerializer(it.getHilo().getTopico().getNombre(), it.getHilo().getTopico().getDescripcion(),
                        new CategoriaSerializer(it.getHilo().getTopico().getCategoria().getNombre(), it.getHilo().getTopico().getCategoria().getDescripcion())),
                        new UserSerializer(it.getHilo().getUsuario().getUser(), it.getHilo().getUsuario().getEmail())),
                new UserSerializer(it.getUsuario().getUser(), it.getUsuario().getEmail()))).toList();
    }

    @PostMapping("/")
    public Comentario crearComentario(@RequestBody SaveComentarioRequest request){
        Usuario usuario = userService.encontrarUsuario(request.getUsername());
        Hilo hilo = hiloService.encontrarHIlo(request.getNombreHilo());
        Comentario comentario = new Comentario(null, request.getMensaje(), hilo, usuario);
        comentarioService.guardarComentario(comentario);
        return comentario;
    }
}