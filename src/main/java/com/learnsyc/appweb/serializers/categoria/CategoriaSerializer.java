package com.learnsyc.appweb.serializers.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CategoriaSerializer {
    @NotNull String nombre;
    @NotNull String descripcion;
}
