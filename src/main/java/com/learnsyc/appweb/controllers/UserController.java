package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.models.Canje;
import com.learnsyc.appweb.serializers.canje.CanjeoResponse;
import com.learnsyc.appweb.serializers.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.learnsyc.appweb.services.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/listar/")
    public List<UserSerializer> listarUsuario() {
        return userService.listarUsuarios().stream().map((it) -> userService.retornarUsuario(it)).toList();
    }

    @PostMapping("/puntuar/")
    public CanjeoResponse puntuar(@Valid @RequestBody PuntuarRequest request){
        return userService.puntuar(request);
    }

    @PostMapping("/canjear")
    public CanjeoResponse canjear(@Valid @RequestBody PuntuarRequest request){
        return userService.canjear(request);
    }
}
