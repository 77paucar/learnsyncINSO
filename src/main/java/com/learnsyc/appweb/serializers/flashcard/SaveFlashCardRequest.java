package com.learnsyc.appweb.serializers.flashcard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveFlashCardRequest {
    @NotEmpty(message = "Dato Vacio")
    @NotBlank(message = "Se detectó una respuesta con espacio en blanco")
    private String concepto;

    @NotEmpty(message = "Dato Vacio")
    @NotBlank(message = "Se detectó una respuesta con espacio en blanco")
    private String respuesta;

    @NotNull(message = "Dato nulo")
    private Long id;
}
