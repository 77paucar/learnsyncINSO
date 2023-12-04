package com.learnsyc.appweb;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.models.Topico;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.HiloRepository;
import com.learnsyc.appweb.services.HiloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HiloServiceTest {

    @Mock
    HiloRepository hiloRepository;

    @InjectMocks
    HiloService hiloService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarHilo() {
        // Given
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hilo1 = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        Hilo hilo2 = new Hilo(2L, "Título 2", "Mensaje 2", topicoMock, usuarioMock);
        List<Hilo> hilos = Arrays.asList(hilo1, hilo2);

        when(hiloRepository.findAll()).thenReturn(hilos);

        // When
        List<Hilo> result = hiloService.listarHilo();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getIdHilo());
        assertEquals("Título 1", result.get(0).getTitulo());
        assertEquals("Mensaje 1", result.get(0).getMensaje());
        assertNotNull(result.get(0).getTopico());
        assertEquals(1L, result.get(0).getTopico().getIdTopico());
        assertEquals("Topico1", result.get(0).getTopico().getNombre());
        assertEquals("Descripcion1", result.get(0).getTopico().getDescripcion());
        assertNotNull(result.get(0).getTopico().getCategoria());
        assertEquals(1L, result.get(0).getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", result.get(0).getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", result.get(0).getTopico().getCategoria().getDescripcion());
        assertNotNull(result.get(0).getUsuario());
        assertEquals(1L, result.get(0).getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(0).getUsuario().getUser());
        assertEquals("Password1", result.get(0).getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(0).getUsuario().getEmail());
        assertEquals(2L, result.get(1).getIdHilo());
        assertEquals("Título 2", result.get(1).getTitulo());
        assertEquals("Mensaje 2", result.get(1).getMensaje());
        assertNotNull(result.get(1).getTopico());
        assertEquals(1L, result.get(1).getTopico().getIdTopico());
        assertEquals("Topico1", result.get(1).getTopico().getNombre());
        assertEquals("Descripcion1", result.get(1).getTopico().getDescripcion());
        assertNotNull(result.get(1).getTopico().getCategoria());
        assertEquals(1L, result.get(1).getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", result.get(1).getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", result.get(1).getTopico().getCategoria().getDescripcion());
        assertNotNull(result.get(1).getUsuario());
        assertEquals(1L, result.get(1).getUsuario().getIdUsuario());
        assertEquals("Usuario1", result.get(1).getUsuario().getUser());
        assertEquals("Password1", result.get(1).getUsuario().getPassword());
        assertEquals("Email@gmail.com", result.get(1).getUsuario().getEmail());
    }

    @Test
    public void testGuardarHilo(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hilo = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        when(hiloRepository.save(hilo)).thenReturn(hilo);
        //When
        Categoria categoria = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topico = new Topico(1L, "Topico1", "Descripcion1", categoria);
        Usuario usuario = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hiloAGuardar = new Hilo(1L, "Título 1", "Mensaje 1", topico, usuario);
        Hilo hiloGuardado;
        hiloGuardado = hiloService.guardarHilo(hiloAGuardar);
        //Then
        assertNotNull(hiloGuardado);
        assertEquals(1L, hiloGuardado.getIdHilo());
        assertEquals("Título 1", hiloGuardado.getTitulo());
        assertEquals("Mensaje 1", hiloGuardado.getMensaje());
        assertNotNull(hiloGuardado.getTopico());
        assertEquals(1L, hiloGuardado.getTopico().getIdTopico());
        assertEquals("Topico1", hiloGuardado.getTopico().getNombre());
        assertEquals("Descripcion1", hiloGuardado.getTopico().getDescripcion());
        assertNotNull(hiloGuardado.getTopico().getCategoria());
        assertEquals(1L, hiloGuardado.getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", hiloGuardado.getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", hiloGuardado.getTopico().getCategoria().getDescripcion());
        assertNotNull(hiloGuardado.getUsuario());
        assertEquals(1L, hiloGuardado.getUsuario().getIdUsuario());
        assertEquals("Usuario1", hiloGuardado.getUsuario().getUser());
        assertEquals("Password1", hiloGuardado.getUsuario().getPassword());
        assertEquals("Email@gmail.com", hiloGuardado.getUsuario().getEmail());
    }

    @Test
    public void testEncontrarHilo(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hilo = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        when(hiloRepository.existsById(1L)).thenReturn(true);
        when(hiloRepository.findByIdHilo(1L)).thenReturn(hilo);
        //When
        Hilo hiloEncontrado = hiloService.encontrarHilo(1L);
        //Then
        assertNotNull(hiloEncontrado);
        assertEquals(1L, hiloEncontrado.getIdHilo());
        assertEquals("Título 1", hiloEncontrado.getTitulo());
        assertEquals("Mensaje 1", hiloEncontrado.getMensaje());
        assertNotNull(hiloEncontrado.getTopico());
        assertEquals(1L, hiloEncontrado.getTopico().getIdTopico());
        assertEquals("Topico1", hiloEncontrado.getTopico().getNombre());
        assertEquals("Descripcion1", hiloEncontrado.getTopico().getDescripcion());
        assertNotNull(hiloEncontrado.getTopico().getCategoria());
        assertEquals(1L, hiloEncontrado.getTopico().getCategoria().getIdCategorias());
        assertEquals("Categoria1", hiloEncontrado.getTopico().getCategoria().getNombre());
        assertEquals("Descripcion1", hiloEncontrado.getTopico().getCategoria().getDescripcion());
        assertNotNull(hiloEncontrado.getUsuario());
        assertEquals(1L, hiloEncontrado.getUsuario().getIdUsuario());
        assertEquals("Usuario1", hiloEncontrado.getUsuario().getUser());
        assertEquals("Password1", hiloEncontrado.getUsuario().getPassword());
        assertEquals("Email@gmail.com", hiloEncontrado.getUsuario().getEmail());
    }

    @Test
    public void testEncontrarHilo_HiloNoEncontrado(){
        Hilo hilo;
        try{
            hilo = hiloService.encontrarHilo(1L);
        }catch (ResourceNotExistsException e){
            assertEquals("El hilo #1 no existe", e.getMessage());
        }
    }

    @Test
    public void testEliminarHilo(){
        Categoria categoriaMock = new Categoria(1L, "Categoria1", "Descripcion1");
        Topico topicoMock = new Topico(1L, "Topico1", "Descripcion1", categoriaMock);
        Usuario usuarioMock = new Usuario(1l, "Usuario1", "Password1", "Email@gmail.com");
        Hilo hilo = new Hilo(1L, "Título 1", "Mensaje 1", topicoMock, usuarioMock);
        hiloRepository.deleteById(1L);
        verify(hiloRepository, times(1)).deleteById(1L);
    }
}