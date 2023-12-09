package com.learnsyc.appweb.serializers.mazo;

import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MazoSerializer {
    private Long id;
    private String titulo;
    private String descripcion;
    private String imagen;
    private UserSerializer usuario;
}
