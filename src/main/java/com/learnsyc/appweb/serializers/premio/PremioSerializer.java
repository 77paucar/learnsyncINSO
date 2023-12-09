package com.learnsyc.appweb.serializers.premio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PremioSerializer {
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    @Size(max = 30, message = "El nombre puede tener máximo 30 carácteres")
    private String nombre;

    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    @Size(max = 200, message = "La descripción puede tener máximo 200 carácteres")
    private String descripcion;

    @NotNull(message = "Dato nulo")
    private int precio;

    @NotEmpty(message = "Dato vacio")
    String imagen;
}