package com.learnsyc.appweb.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.serializers.categoria.CategoriaSerializer;
import com.learnsyc.appweb.services.CategoriaService;

@RestController
@RequestMapping("categoria")
@CrossOrigin(origins = "https://boisterous-sopapillas-1c3767.netlify.app")
public class CategoriaController {

    @Autowired CategoriaService categoriaService; 

    @GetMapping("/listar/")
    public List<CategoriaSerializer> listarCategoria() {
        return categoriaService.listarCategorias().stream().map((it) -> new CategoriaSerializer(it.getNombre(), it.getDescripcion())).toList();
    }

    @PostMapping("/")
    public Categoria crearCategoria(@Valid @RequestBody CategoriaSerializer request) {
        Categoria categoria = new Categoria(null, request.getNombre(), request.getDescripcion());
        return categoriaService.guardarCategoria(categoria);
    }

    @DeleteMapping("/")
    public Categoria eliminarCategoria(@Valid @RequestBody DeleteCategoriaRequest request){
        return categoriaService.eliminarCategoria(request);
    }
}
