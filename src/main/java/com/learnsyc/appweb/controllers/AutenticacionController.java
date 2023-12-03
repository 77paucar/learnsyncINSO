package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.serializers.usuario.RecuperarContraRequest;
import com.learnsyc.appweb.serializers.usuario.SaveUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnsyc.appweb.services.AutenticacionServices;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserRequest;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserResponse;

@RestController
@RequestMapping("autenticacion")
@CrossOrigin(origins = "http://localhost:4200")
public class AutenticacionController {

    @Autowired
    private AutenticacionServices autenticacionServices;

    @PostMapping("/register/")
    public AuthenticationUserResponse crearUsuario(@Valid @RequestBody SaveUserRequest request) {
        return autenticacionServices.registrarUsuario(request);
    }

    @PostMapping("/recuperar-contra/")
    public String recuperarContraseña(@Valid @RequestBody RecuperarContraRequest request){
        return autenticacionServices.recuperarContraseña(request);
    }

    @PostMapping("/authentication/")
    public ResponseEntity<AuthenticationUserResponse> iniciarSesion(@Valid @RequestBody AuthenticationUserRequest request) throws Exception {
        return ResponseEntity.ok(autenticacionServices.autenticarUsuario(request));
    }
}