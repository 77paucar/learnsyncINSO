package com.learnsyc.appweb;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.UserRepository;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import com.learnsyc.appweb.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarUsuarios() {
        // Given
        Usuario usuario1 = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        Usuario usuario2 = new Usuario(2L, "User2", "Password2", "Email2@gmail.com");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(userRepository.findAll()).thenReturn(usuarios);

        // When
        List<Usuario> result = userService.listarUsuarios();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("User1", result.get(0).getUser());
        assertEquals("Password1", result.get(0).getPassword());
        assertEquals("Email1@gmail.com", result.get(0).getEmail());
        assertEquals("User2", result.get(1).getUser());
        assertEquals("Password2", result.get(1).getPassword());
        assertEquals("Email2@gmail.com", result.get(1).getEmail());
    }

    @Test
    public void testEncontrarUsuarioPorUser_UsuarioExistente() {
        // Given
        Usuario usuario = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        when(userRepository.findByUser("User1")).thenReturn(Optional.of(usuario));

        // When
        Usuario result = userService.encontrarUsuarioPorUser("User1");

        // Then
        assertNotNull(result);
        assertEquals(usuario, result);
    }

    @Test
    public void testEncontrarUsuarioPorUser_UsuarioNoExistente() {
        // Given
        when(userRepository.findByUser("UserNotFound")).thenReturn(Optional.empty());

        // When, Then
        assertThrows(ResourceNotExistsException.class, () -> userService.encontrarUsuarioPorUser("UserNotFound"));
    }

    @Test
    public void testEncontrarUsuarioPorEmail_UsuarioExistente() {
        // Given
        Usuario usuario = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");
        when(userRepository.existsUsuarioByEmail("Email1@gmail.com")).thenReturn(true);
        when(userRepository.findByEmail("Email1@gmail.com")).thenReturn(usuario);

        // When
        Usuario result = userService.encontrarUsuarioPorEmail("Email1@gmail.com");

        // Then
        assertNotNull(result);
        assertEquals(usuario, result);
    }

    @Test
    public void testEncontrarUsuarioPorEmail_UsuarioNoExistente() {
        // Given
        when(userRepository.existsUsuarioByEmail("EmailNotFound@gmail.com")).thenReturn(false);

        // When, Then
        assertThrows(ResourceNotExistsException.class, () -> userService.encontrarUsuarioPorEmail("EmailNotFound@gmail.com"));
    }

    @Test
    public void testGuardarCambios() {
        // Given
        Usuario usuario = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");

        // When
        userService.guardarCambios(usuario);

        // Then
        verify(userRepository, times(1)).saveAndFlush(any(Usuario.class));
    }

    @Test
    public void testRetornarUsuario() {
        // Given
        Usuario usuario = new Usuario(1L, "User1", "Password1", "Email1@gmail.com");

        // When
        UserSerializer result = userService.retornarUsuario(usuario);

        // Then
        assertNotNull(result);
        assertEquals("User1", result.getUser());
        assertEquals("Email1@gmail.com", result.getEmail());
    }
}