package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.ConfirmationToken;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.ConfirmationTokenRepository;
import com.learnsyc.appweb.serializers.usuario.AuthenticationUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired private EmailService emailService;

    public void enviarEmail(Usuario usuario) {
        String token = generarToken(usuario);
        String url = "http://localhost:8080/autenticacion/confirmation-token/"+token;
        String mensaje = "Felicidades "+usuario.getUser()+" por registrar su cuenta, estas a un solo paso de poder hacer uso " +
                "de las funciones de Learnsync, entra a este link para que puedas registrate," +url;
        emailService.sendEmail(usuario.getEmail(), "Activacion de cuenta", mensaje);
    }

    public ConfirmationToken encontrarToken(String token){
        if(!confirmationTokenRepository.existsConfirmationTokenByToken(token)){
            throw new ResourceNotExistsException("Token no válido");
        }
        return confirmationTokenRepository.findByToken(token);
    }

    public void guardarCambios(ConfirmationToken confirmationToken){confirmationTokenRepository.saveAndFlush(confirmationToken);}

    private String generarToken(Usuario usuario){
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(null, token, usuario);
        return confirmationTokenRepository.save(confirmationToken).getToken();
    }
}
