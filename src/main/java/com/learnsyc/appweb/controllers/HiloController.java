package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.serializers.hilos.DeleteHiloRequest;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.hilos.SaveHiloRequest;
import com.learnsyc.appweb.services.HiloService;
import com.learnsyc.appweb.services.TopicoService;
import com.learnsyc.appweb.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hilo")
@CrossOrigin(origins = "http://localhost:4200")
public class HiloController {
    @Autowired HiloService hiloService;
    @Autowired UserService userService;
    @Autowired TopicoService topicoService;

    @GetMapping("/")
    public List<HiloSerializer> listarHilo() {
        return hiloService.listarHilo().stream().map((it) -> hiloService.retornarHilo(it)).toList();
    }
    @PostMapping("/")
    public Hilo crearHilo(@Valid @RequestBody SaveHiloRequest request) {
        Usuario usuario = userService.encontrarUsuarioPorUser(request.getUsername());
        Topico topico = topicoService.buscarTopico(request.getTopicname());
        Hilo hilo = new Hilo(null, request.getTitulo(), request.getMensaje(), topico, usuario);
        return hiloService.guardarHilo(hilo);
    }
    @DeleteMapping("/")
    public Hilo eliminarHilo(@Valid @RequestBody DeleteHiloRequest request){
        return hiloService.eliminarHilo(request);
    }
    @PostMapping("/encontrar/")
    public HiloSerializer encontrarHilo(@Valid @RequestBody Long id) { //En duda de por que hasta aca xd
        Hilo hilo = hiloService.encontrarHilo(id);
        return hiloService.retornarHilo(hilo);
    }
}
