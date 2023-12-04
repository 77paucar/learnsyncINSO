package com.learnsyc.appweb.serializers.comentario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveComentarioRequest {
    @NotEmpty(message = "Dato vacio")
    @Size(max=200, message = "El mensaje puede tener un máximo de 200 carácteres")
    String mensaje;
    @NotEmpty(message = "Dato vacio")
    String username;
    @NotNull(message = "Dato vacio")
    Long idHilo;
}
