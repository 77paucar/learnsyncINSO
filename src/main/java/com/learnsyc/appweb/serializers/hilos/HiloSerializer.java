package com.learnsyc.appweb.serializers.hilos;

import com.learnsyc.appweb.serializers.topico.TopicoSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HiloSerializer {
    Long id;
    String titulo;
    String mensaje;
    LocalDate fechaCreacion; //Cambiar a LocalDateTime
    TopicoSerializer topico;
    UserSerializer usuario;
}
