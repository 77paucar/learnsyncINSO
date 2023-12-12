package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Comentario;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.comentario.ComentarioSerializer;
import com.learnsyc.appweb.serializers.comentario.SaveComentarioRequest;
import com.learnsyc.appweb.services.ComentarioService;
import com.learnsyc.appweb.services.HiloService;
import com.learnsyc.appweb.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comentario")
@CrossOrigin(origins = "https://velvety-dusk-4569ef.netlify.app")
public class ComentarioController {
    @Autowired ComentarioService comentarioService;
    @Autowired UserService userService;
    @Autowired HiloService hiloService;

    @GetMapping("/")
    public List<ComentarioSerializer> listarComentario(){
        return comentarioService.listarComentario().stream().map((it) ->comentarioService.retornarComentario(it)).toList();
    }

    @PostMapping("/")
    public Comentario crearComentario(@Valid @RequestBody SaveComentarioRequest request){
        Usuario usuario = userService.encontrarUsuarioPorUser(request.getUsername());
        Hilo hilo = hiloService.encontrarHilo(request.getIdHilo());
        Comentario comentario = new Comentario(null, request.getMensaje(), hilo, usuario);
        return comentarioService.guardarComentario(comentario);
    }
}
