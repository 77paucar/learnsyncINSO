package com.learnsyc.appweb.services;

import com.learnsyc.appweb.excepciones.ResourceNotExistsException;
import com.learnsyc.appweb.models.Hilo;
import com.learnsyc.appweb.repositories.HiloRepository;
import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiloService {

    @Autowired
    HiloRepository hiloRepository;
    @Autowired
    TopicoService topicoService;
    @Autowired UserService userService;

    public List<Hilo> listarHilo(){
        return hiloRepository.findAll();
    }

    public Hilo guardarHilo(Hilo hilo){
        return hiloRepository.save(hilo);
    }

    public Hilo encontrarHilo(Long id){
        if(!hiloRepository.existsById(id)){
            throw new ResourceNotExistsException("El hilo #"+id+" no existe");
        }
        return hiloRepository.findByIdHilo(id);
    }

    public HiloSerializer retornarHilo(Hilo hilo){
        return new HiloSerializer(hilo.getIdHilo(), hilo.getTitulo(), hilo.getMensaje(), hilo.getFechaCreacion(),
                topicoService.retornarTopico(hilo.getTopico()), userService.retornarUsuario(hilo.getUsuario()));
    }
}