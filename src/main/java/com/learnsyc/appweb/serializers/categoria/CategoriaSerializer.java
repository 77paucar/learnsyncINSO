package com.learnsyc.appweb.serializers.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaSerializer {
    @NotNull(message = "Dato no válido")
    @NotEmpty(message = "Dato vacio")
    @NotBlank(message = "No es valido un dato con solo espacio en blanco")
    @Size(max = 20 , message = "Las categorias pueden tener un maximo de 20 caracteres")
    String nombre;
    String descripcion;
}
