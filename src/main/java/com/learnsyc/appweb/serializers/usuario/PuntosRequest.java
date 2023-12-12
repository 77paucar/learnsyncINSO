package com.learnsyc.appweb.serializers.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PuntosRequest {
    String username;
    int puntos;
}
