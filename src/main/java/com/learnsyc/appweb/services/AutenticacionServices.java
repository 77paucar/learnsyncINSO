package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.EmailConfirmedException;
import com.learnsyc.appweb.excepciones.ExpiredToken;
import com.learnsyc.appweb.excepciones.ResourceAlreadyExistsException;
import com.learnsyc.appweb.excepciones.UserNotActivated;
import com.learnsyc.appweb.models.ConfirmationToken;
import com.learnsyc.appweb.repositories.UserRepository;
import com.learnsyc.appweb.serializers.usuario.*;
import com.learnsyc.appweb.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.util.EncryptionUtil;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AutenticacionServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired private EmailService emailService;

    @Autowired private TokenService tokenService;

    @Autowired private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserSerializer registrarUsuario(SaveUserRequest request) {
        Usuario usuario = new Usuario(null, request.getUser(), passwordEncoder.encode(request.getPassword()), request.getEmail());
        if(userRepository.existsUsuarioByUser(usuario.getUser())){
            throw new ResourceAlreadyExistsException("El usuario "+usuario.getUser()+" existe");
        }
        if(userRepository.existsUsuarioByEmail(usuario.getEmail())){
            throw new ResourceAlreadyExistsException("El email ya ha sido usado para la creación de otro usuario");
        }
        usuario.setRole(Role.ADMIN);
        userRepository.save(usuario);
        tokenService.enviarEmail(usuario);
        return userService.retornarUsuario(usuario);
    }

    public UserSerializer registrarAdmin(SaveUserRequest request) {
        Usuario usuario = new Usuario(null, request.getUser(), passwordEncoder.encode(request.getPassword()), request.getEmail());
        if(userRepository.existsUsuarioByUser(usuario.getUser())){
            throw new ResourceAlreadyExistsException("El usuario "+usuario.getUser()+" existe");
        }
        if(userRepository.existsUsuarioByEmail(usuario.getEmail())){
            throw new ResourceAlreadyExistsException("El email ya ha sido usado para la creación de otro usuario");
        }
        usuario.setRole(Role.ADMIN);
        userRepository.save(usuario);
        tokenService.enviarEmail(usuario);
        return userService.retornarUsuario(usuario);
    }


    public String ConfirmarCuenta(String token){
        ConfirmationToken confirmationToken = tokenService.encontrarToken(token);
        if(confirmationToken.getFechaActivacion() != null){
            throw new EmailConfirmedException("Este email ya ha sido confirmado");
        }
        LocalDateTime fechaExpiracion = confirmationToken.getFechaExpiracion();
        if(fechaExpiracion.isBefore(LocalDateTime.now())){
            throw new ExpiredToken("Token expirado");
        }
        confirmationToken.setFechaActivacion(LocalDateTime.now());
        tokenService.guardarCambios(confirmationToken);
        Usuario usuario = confirmationToken.getUsuario();
        usuario.setEnable(true);
        userService.guardarCambios(usuario);
        return "<h1>Muchas gracias por activar su cuenta</h1>";
    }

    public AuthenticationUserResponse autenticarUsuario(AuthenticationUserRequest request) throws Exception {
        Optional<Usuario> usuario = userRepository.findByUser(request.getUser());
        if(usuario.isPresent()){
            if(usuario.get().isEnable()){
                try{
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
                    return new AuthenticationUserResponse(EncryptionUtil.encrypt(jwtTokenUtil.generateToken(usuario.get())));
                }catch (AuthenticationException e){
                    //pass to the throw.
                }
            }else {
                throw new UserNotActivated("Cuenta no activada");
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario y/o password incorrectos");
    }
}
