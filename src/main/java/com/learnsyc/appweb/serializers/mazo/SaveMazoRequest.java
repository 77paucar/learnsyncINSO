package com.learnsyc.appweb.serializers.mazo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveMazoRequest {
    @NotEmpty(message = "Dato Vacio")
    @NotBlank(message = "Se detectó una respuesta con espacio en blanco")
    @Size(max = 30, message = "El titulo puede tener máximo 30 carácteres")
    String titulo;

    @NotEmpty(message = "Dato Vacio")
    @NotBlank(message = "Se detectó una respuesta con espacio en blanco")
    @Size(max = 200, message = "La descripción puede tener máximo 200 carácteres")
    String descripcion;

    @NotEmpty(message = "Dato Vacio")
    @NotBlank(message = "Se detectó una respuesta con espacio en blanco")
    String imagen;

    @NotEmpty(message = "Dato Vacio")
    @NotBlank(message = "Se detectó una respuesta con espacio en blanco")
    String username;
}
