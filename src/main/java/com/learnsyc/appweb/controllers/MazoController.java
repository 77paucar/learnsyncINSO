package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Mazo;
import com.learnsyc.appweb.serializers.mazo.MazoSerializer;
import com.learnsyc.appweb.serializers.mazo.SaveMazoRequest;
import com.learnsyc.appweb.services.MazoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mazo")
@CrossOrigin(origins = "https://velvety-dusk-4569ef.netlify.app")
public class MazoController {
    @Autowired
    MazoService mazoService;

    @GetMapping("/listar/")
    public List<MazoSerializer> listarMazo(){
        return mazoService.listarMazos().stream().map((it)->mazoService.retornarMazo(it)).toList();
    }

    @PostMapping("/")
    public Mazo crearMazo(@Valid @RequestBody SaveMazoRequest request){
        return mazoService.crearMazo(request);
    }
}
