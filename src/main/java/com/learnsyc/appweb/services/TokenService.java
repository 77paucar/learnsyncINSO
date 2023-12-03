package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.ConfirmationToken;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService { //En duda si eliminarlo debido a que se genera un token del jwt

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    private void enviarEmail(String token) {
        String url = "localhost:8080/user/confirmation-token?"+token;

    }

    public ConfirmationToken encontrarToken(String token){
        if(!confirmationTokenRepository.existsConfirmationTokenByToken(token)){
            throw new ResourceNotExistsException("Token no válido");
        }
        return confirmationTokenRepository.findByToken(token);
    }

    public String generarToken(Usuario usuario){
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(null, token, usuario);
        confirmationTokenRepository.save(confirmationToken);
        return token;
    }
}
