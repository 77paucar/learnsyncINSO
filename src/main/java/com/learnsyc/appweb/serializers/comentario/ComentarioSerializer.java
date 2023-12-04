package com.learnsyc.appweb.serializers.comentario;

import com.learnsyc.appweb.serializers.hilos.HiloSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ComentarioSerializer {
    Long id;
    String mensaje;
    LocalDate fechaCreacion;
    HiloSerializer hiloSerializer;
    UserSerializer userSerializer;
}
