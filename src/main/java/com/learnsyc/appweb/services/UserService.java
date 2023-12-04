package com.learnsyc.appweb.services;

import java.util.List;
import java.util.Optional;

import com.learnsyc.appweb.excepciones.*;
import com.learnsyc.appweb.serializers.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
    public void guardarCambios(Usuario usuario){userRepository.saveAndFlush(usuario);}

    public UserSerializer retornarUsuario(Usuario usuario){
        return new UserSerializer(usuario.getUser(), usuario.getEmail(), usuario.getRole());
    }
}