package com.learnsyc.appweb.serializers.premio;

import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PremioSerializer {
    String nombre;
    String descripcion;
    int precio;
    String imagen;
}