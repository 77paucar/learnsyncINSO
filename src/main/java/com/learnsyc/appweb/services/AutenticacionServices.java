package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.*;
import com.learnsyc.appweb.models.ConfirmationToken;
import com.learnsyc.appweb.repositories.ConfirmationTokenRepository;
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
import java.util.UUID;

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

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public UserSerializer registrarUsuario(SaveUserRequest request) {
        Usuario usuario = new Usuario(null, request.getUser(), passwordEncoder.encode(request.getPassword()), request.getEmail());
        if(userRepository.existsUsuarioByUser(usuario.getUser())){
            throw new ResourceAlreadyExistsException("El usuario "+usuario.getUser()+" existe");
        }
        if(userRepository.existsUsuarioByEmail(usuario.getEmail())){
            throw new ResourceAlreadyExistsException("El email ya ha sido usado para la creación de otro usuario");
        }
        usuario.setRole(Role.STUDENT);
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
        return " <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"100%\">\n" +
                "    <tr>\n" +
                "      <td style=\"text-align: center; padding: 50px 0;\">\n" +
                "        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" width=\"600\"\n" +
                "          style=\"background-color: #ffffff; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);\">\n" +
                "          <tr>\n" +
                "            <td style=\"padding: 40px;\">\n" +
                "              <h6 style=\"text-align: center; font-size: 18px; color: #000; text-transform: uppercase; font-weight: bold; font-family: 'Roboto Mono', monospace;\">\n" +
                "                LEARNSYNC</h6>\n" +
                "              <h3 style=\"text-align: center; font-size: 24px; color: #007bff; font-weight: bold; font-family: 'Roboto', sans-serif;\">\n" +
                "                ACTIVACIÓN DE CUENTA</h3>\n" +
                "              <p style=\"text-align: center; font-size: 18px; color: #000;\">Se activó la cuenta correctamente.</p>\n" +
                "              <div style=\"text-align: center; margin-top: 30px;\">\n" +
                "                <a href=\"https://velvety-dusk-4569ef.netlify.app/login\"\n" +
                "                  style=\"display: inline-block; padding: 12px 24px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px; font-size: 18px; font-weight: bold;\">\n" +
                "                  Iniciar sesión\n" +
                "                </a>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>";
    }

    public AuthenticationUserResponse autenticarUsuario(AuthenticationUserRequest request) throws Exception {
        Optional<Usuario> usuario = userRepository.findByUser(request.getUser());
        if(usuario.isPresent()){
            if(usuario.get().isEnable()){
                try{
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
                    return new AuthenticationUserResponse(jwtTokenUtil.generateToken(usuario.get()));
                }catch (AuthenticationException e){
                    //pass to the throw.
                }
            }else {
                throw new UserNotActivated("Cuenta no activada");
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario y/o password incorrectos");
    }

    public RecuperarContraResponse recuperarContra(String email) {
        if(!userRepository.existsUsuarioByEmail(email)){
            throw new ResourceNotExistsException("No existe usuario ligado a ese correo");
        }
        Usuario usuario = userRepository.findByEmail(email);
        String token = confirmationTokenRepository.findByUsuario(usuario).getToken();
        String url = "https://velvety-dusk-4569ef.netlify.app/reset-password/"+token;
        String mensaje = "Hola "+usuario.getUser()+" vemos que olvidaste tu contraseña y en Learnsync nos gusta la tranquilidad de nuestros usuarios." +
                "Ingresa a este link para que reestablezcas tu contraseña y puedas seguir disfrutando las funcioens de Learnsync."
                +"Link: "+url;
        emailService.sendEmail(email, "Reestablecer contraseña", mensaje);
        return new RecuperarContraResponse("Correo enviado");
    }
}
