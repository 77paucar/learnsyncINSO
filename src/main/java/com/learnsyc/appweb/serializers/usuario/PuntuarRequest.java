package com.learnsyc.appweb.serializers.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PuntuarRequest {
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    String username;
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    String nombre;
    @NotNull(message = "Dato nulo")
    int puntos;
}
