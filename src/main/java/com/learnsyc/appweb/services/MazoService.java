package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.Mazo;
import com.learnsyc.appweb.models.Usuario;
import com.learnsyc.appweb.repositories.MazoRepository;
import com.learnsyc.appweb.serializers.mazo.MazoSerializer;
import com.learnsyc.appweb.serializers.mazo.SaveMazoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MazoService {
    @Autowired
    MazoRepository mazoRepository;
    @Autowired
    UserService userService;

    public List<Mazo> listarMazos(){ return mazoRepository.findAll();}

    public Mazo crearMazo(SaveMazoRequest request){
        Usuario usuario = userService.encontrarUsuarioPorUser(request.getUsername());
        Mazo mazo = new Mazo(null, request.getTitulo(), request.getDescripcion(), request.getImagen(), usuario);
        return mazoRepository.save(mazo);
    }

    public Mazo encontrarMazo(Long id){
        return mazoRepository.findByIdMazo(id);
    }

    public MazoSerializer retornarMazo(Mazo mazo){
        return new MazoSerializer(mazo.getIdMazo(), mazo.getTitulo(), mazo.getDescripcion(), mazo.getImagen(), userService.retornarUsuario(mazo.getUsuario()));
    }
}
