package com.learnsyc.appweb.serializers.mazo;

import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MazoSerializer {
    Long id;
    String titulo;
    String descripcion;
    String imagen;
    UserSerializer usuario;
}
