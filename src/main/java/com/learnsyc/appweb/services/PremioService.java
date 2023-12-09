package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Premio;
import com.learnsyc.appweb.repositories.PremioRepository;
import com.learnsyc.appweb.serializers.premio.PremioSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {
    @Autowired
    private PremioRepository premioRepository;

    public List<Premio> listarPremios() {
        return premioRepository.findAll();
    }

    public Premio crearPremio(Premio premio) {
        return premioRepository.save(premio);
    }

    public PremioSerializer retornarPremio(Premio premio) {
        return new PremioSerializer(premio.getNombre(), premio.getDescripcion(), premio.getPrecio(), premio.getImagen());
    }

    public Premio encontrarPremio(String nombre) {
        return premioRepository.findByNombre(nombre);
    }
}