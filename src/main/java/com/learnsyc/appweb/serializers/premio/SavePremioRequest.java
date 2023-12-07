package com.learnsyc.appweb.serializers.premio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavePremioRequest {
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    String nombre;

    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    String descripcion;

    @NotNull(message = "Dato nulo")
    int precio;

    @NotEmpty(message = "Dato vacio")
    String imagen;

    @NotEmpty(message = "Dato vacio")
    String username;
}