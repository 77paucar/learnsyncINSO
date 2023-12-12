package com.learnsyc.appweb.services;

import java.util.List;
import java.util.Optional;

import com.learnsyc.appweb.excepciones.*;
import com.learnsyc.appweb.models.Canje;
import com.learnsyc.appweb.models.Premio;
import com.learnsyc.appweb.repositories.CanjeoRepository;
import com.learnsyc.appweb.repositories.ConfirmationTokenRepository;
import com.learnsyc.appweb.serializers.canje.CanjeoResponse;
import com.learnsyc.appweb.serializers.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private PremioService premioService;
    @Autowired private CanjeoRepository canjeoRepository;

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
        return new UserSerializer(usuario.getUser(), usuario.getEmail(), usuario.getRole(), usuario.getNroPuntos());
    }

    public UserSerializer changePassword(String password, String token) {
        Usuario usuario = confirmationTokenRepository.findByToken(token).getUsuario();
        usuario.setPassword(passwordEncoder.encode(password));
        guardarCambios(usuario);
        return retornarUsuario(usuario);
    }

    public CanjeoResponse puntuar(PuntosRequest request) {
        Usuario usuario = encontrarUsuarioPorUser(request.getUsername());
        usuario.setNroPuntos(usuario.getNroPuntos()+ request.getPuntos());
        guardarCambios(usuario);
        return new CanjeoResponse("Comentario puntuado");
    }

    public CanjeoResponse canjear(PuntuarRequest request) {
        Usuario usuario = encontrarUsuarioPorUser(request.getUsername());
        Premio premio = premioService.encontrarPremio(request.getNombre());
        try{
            usuario.setNroPuntos(usuario.getNroPuntos() - request.getPuntos());
        }catch (RuntimeException e){
            throw new RuntimeException("Puntaje insuficiente");
        }
        guardarCambios(usuario);
        canjeoRepository.save(new Canje(usuario, premio, request.getPuntos()));
        return new CanjeoResponse("Canjeo efectuado");
    }
}