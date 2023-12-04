package com.learnsyc.appweb.controllers;

import java.util.List;

import com.learnsyc.appweb.serializers.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learnsyc.appweb.services.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "https://velvety-dusk-4569ef.netlify.app")
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/listar/")
    public List<UserSerializer> listarUsuario() {
        return userService.listarUsuarios().stream().map((it) -> userService.retornarUsuario(it)).toList();
    }

    //Analizar si juntar AutenticacionController con UserController
}
