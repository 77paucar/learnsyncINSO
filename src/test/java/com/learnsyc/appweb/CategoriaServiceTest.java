package com.learnsyc.appweb;

import com.learnsyc.appweb.excepciones.ResourceAlreadyExistsException;
import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Categoria;
import com.learnsyc.appweb.repositories.CategoriaRepository;
import com.learnsyc.appweb.services.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    CategoriaRepository categoriaRepository;

    @InjectMocks
    CategoriaService categoriaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarCategorias() {
        // Given
        Categoria categoria1 = new Categoria(1L, "Nombre 1", "Descripción 1");
        Categoria categoria2 = new Categoria(2L, "Nombre 2", "Descripción 2");
        List<Categoria> categorias = Arrays.asList(categoria1, categoria2);

        when(categoriaRepository.findAll()).thenReturn(categorias);

        // When
        List<Categoria> result = categoriaService.listarCategorias();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Nombre 1", result.get(0).getNombre());
        assertEquals("Descripción 1", result.get(0).getDescripcion());
        assertEquals("Nombre 2", result.get(1).getNombre());
        assertEquals("Descripción 2", result.get(1).getDescripcion());
    }

    @Test
    public void testGuardarCategoria() {
        //Give
        Categoria categoriaMock = new Categoria(1L, "Nombre 1", "Descripcion 1");
        when(categoriaRepository.existsCategoriaByNombre(categoriaMock.getNombre())).thenReturn(false);
        when(categoriaRepository.save(categoriaMock)).thenReturn(categoriaMock);
        //When
        Categoria categoriaAGuardar = new Categoria(1L, "Nombre 1", "Descripcion 1");
        Categoria categoriaGuardada;
        categoriaGuardada = categoriaService.guardarCategoria(categoriaAGuardar);
        //Then
        assertNotNull(categoriaGuardada);
        assertEquals(1L, categoriaGuardada.getIdCategorias());
        assertEquals("Nombre 1", categoriaGuardada.getNombre());
        assertEquals("Descripcion 1", categoriaGuardada.getDescripcion());
    }

    @Test
    public void testGuardarCategoria_CategoriaExistente(){
        Categoria categoriaAGuardar1 = new Categoria(1L, "Nombre 1", "Descripcion 1");
        Categoria categoriaGuardada1;
        categoriaGuardada1 = categoriaService.guardarCategoria(categoriaAGuardar1);
        Categoria categoriaAGuardar2 = new Categoria(2L, "Nombre 1", "Descripcion 2");
        Categoria categoriaGuardada2;
        try{
            categoriaGuardada2 = categoriaService.guardarCategoria(categoriaAGuardar2);
        }catch (ResourceAlreadyExistsException e2){
            assertEquals(categoriaGuardada1.getNombre(), categoriaAGuardar2.getNombre());
            assertEquals("La categoria "+categoriaAGuardar2.getNombre()+" existe", e2.getMessage());
        }
    }

    @Test
    public void testEncontrarCategoria(){
        //Give
        Categoria categoriaMock = new Categoria(1L, "Nombre 1", "Descripcion 1");
        when(categoriaRepository.findByNombre("Nombre 1")).thenReturn(categoriaMock);
        //When
        Categoria categoria = categoriaService.encontrarCategoria("Nombre 1");
        //Then
        assertNotNull(categoria);
        assertEquals(1L, categoria.getIdCategorias());
        assertEquals("Nombre 1", categoria.getNombre());
        assertEquals("Descripcion 1", categoria.getDescripcion());
    }

    @Test
    public void testEncontrarCategoria_CategoriaNoEncontrada(){
        Categoria categoria;
        try{
            categoria = categoriaService.encontrarCategoria("Nombre 1");
        }catch (ResourceNotExistsException e){
            assertEquals("La categoria Nombre 1 no existe", e.getMessage());
        }
    }
}