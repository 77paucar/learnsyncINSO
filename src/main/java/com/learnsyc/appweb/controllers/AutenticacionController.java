package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.serializers.usuario.*;
import com.learnsyc.appweb.services.TokenService;
import com.learnsyc.appweb.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnsyc.appweb.services.AutenticacionServices;

@RestController
@RequestMapping("autenticacion")
@CrossOrigin(origins = "http://localhost:4200")
public class AutenticacionController {

    @Autowired
    private AutenticacionServices autenticacionServices;
    @Autowired
    private UserService userService;

    @PostMapping("/register/")
    public UserSerializer crearUsuario(@Valid @RequestBody SaveUserRequest request) {
        return autenticacionServices.registrarUsuario(request);
    }

    @PostMapping("/register-admin/")
    public UserSerializer registrarAdmin(@Valid @RequestBody SaveUserRequest request){
        return autenticacionServices.registrarAdmin(request);
    }

    @GetMapping("/confirmation-token/{token}")
    public String activarCuenta(@PathVariable(name = "token") String token){
        return autenticacionServices.ConfirmarCuenta(token);
    }
    @PostMapping("/authentication/")
    public ResponseEntity<AuthenticationUserResponse> iniciarSesion(@Valid @RequestBody AuthenticationUserRequest request) throws Exception {
        return ResponseEntity.ok(autenticacionServices.autenticarUsuario(request));
    }

    @PostMapping("/recuperar-contra/")
    public RecuperarContraResponse recuperarContra(@Valid @RequestBody String email){
        return autenticacionServices.recuperarContra(email);
    }

    @PostMapping("/change-pass/{token}")
    public UserSerializer changePass(@PathVariable(name = "token") String token, @Valid @RequestBody String password){
        return userService.changePassword(password, token);
    }
}