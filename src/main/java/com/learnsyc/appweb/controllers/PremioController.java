package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.Premio;
import com.learnsyc.appweb.serializers.premio.PremioSerializer;
import com.learnsyc.appweb.services.PremioService;
import com.learnsyc.appweb.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("premio")
@CrossOrigin(origins = "https://velvety-dusk-4569ef.netlify.app")
public class PremioController {
    @Autowired
    private PremioService premioService;
    @Autowired
    private UserService userService;

    @GetMapping("/listar/")
    public List<PremioSerializer> listarPremios(){
        return premioService.listarPremios().stream().map((it) -> premioService.retornarPremio(it)).toList();
    }

    @PostMapping("/")
    public Premio subirPremio(@Valid @RequestBody PremioSerializer request){
        Premio premio = new Premio(null, request.getNombre(), request.getDescripcion(), request.getPrecio(), request.getImagen());
        return premioService.crearPremio(premio);
    }
}