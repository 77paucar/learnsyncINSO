package com.learnsyc.appweb.serializers.canje;

import com.learnsyc.appweb.serializers.premio.PremioSerializer;
import com.learnsyc.appweb.serializers.usuario.UserSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CanjeSerializer {
    private UserSerializer usuario;
    private PremioSerializer premio;
    private int precio;
    private LocalDate fecha;
}
