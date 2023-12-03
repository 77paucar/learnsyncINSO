package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.serializers.topico.*;
import com.learnsyc.appweb.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.services.TopicoService;

@RestController
@RequestMapping("topico")
@CrossOrigin(origins = {"https://boisterous-sopapillas-1c3767.netlify.app"})
public class TopicoController {
    @Autowired TopicoService topicoService;
    @Autowired CategoriaService categoriaService;

    @GetMapping("/")
    public List<TopicoSerializer> listarTopico() {
        return topicoService.listarTopico().stream().map((it) -> topicoService.retornarTopico(it)).toList();
    }


    @PostMapping("/listar/")
    public List<TopicoSerializer> listarTopicoPorCategoria(@Valid @RequestBody  Topico request){
        Categoria categoria = categoriaService.encontrarCategoria(request.getCategoria().getNombre());
        return topicoService.listarTopicoPorCategoria(categoria).stream().map((it) -> topicoService.retornarTopico(it)).toList();
    }

    @PostMapping("/")
    public Topico crearTopico(@Valid @RequestBody SaveTopicoRequest request) {
        Categoria categoria = categoriaService.encontrarCategoria(request.getNombreCategoria());
        Topico topico = new Topico(null, request.getNombre(), request.getDescripcion(), categoria);
        return topicoService.guardarTopico(topico);
    }

    @PutMapping("/")
    public TopicoSerializer editarTopico(@Valid @RequestBody EditarTopicoRequest request){
        return topicoService.editarTopico(request);
    }

    @DeleteMapping("/")
    public Topico eliminarTopico(@Valid @RequestBody EliminarTopicoRequest request){
        return topicoService.eliminarTopico(request);
    }

    @PostMapping("/buscar/")
    public TopicoSerializer buscarTopico(@Valid @RequestBody BuscarTopicoRequest request){
        //Buscar por hilos y comentarios (IDEA)
        Topico topico = topicoService.buscarTopico(request.getNombre());
        return topicoService.retornarTopico(topico);
    }

    @PostMapping("/encontrar/")
    public TopicoSerializer encontrarTopico(@Valid @RequestBody Long request){
        Topico topico = topicoService.encontrarTopico(request);
        return topicoService.retornarTopico(topico);
    }
}