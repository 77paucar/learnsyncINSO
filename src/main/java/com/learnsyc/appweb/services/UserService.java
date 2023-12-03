package com.learnsyc.appweb.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.learnsyc.appweb.excepciones.*;
import com.learnsyc.appweb.models.ConfirmationToken;
import com.learnsyc.appweb.repositories.ConfirmationTokenRepository;
import com.learnsyc.appweb.serializers.usuario.*;
import com.learnsyc.appweb.util.EncryptionUtil;
import com.learnsyc.appweb.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public List<Usuario> listarUsuarios() {
        return userRepository.findAll();
    }

    public Usuario encontrarUsuarioPorUser(String user) {
        Optional<Usuario> usuario = userRepository.findByUser(user);
        if(usuario.isEmpty()){
            throw new ResourceNotExistsException("El usuario "+user+" no existe");
        }
        return usuario.get();
    }

    public Usuario encontrarUsuarioPorEmail(String email){
        if(!userRepository.existsUsuarioByEmail(email)){
            throw new ResourceNotExistsException(("Usuario no encontrado"));
        }
        return userRepository.findByEmail(email);
    }
    public void guardarCambios(Usuario usuario){userRepository.saveAndFlush(usuario);}

    public UserSerializer retornarUsuario(Usuario usuario){
        return new UserSerializer(usuario.getUser(), usuario.getEmail());
    }
}