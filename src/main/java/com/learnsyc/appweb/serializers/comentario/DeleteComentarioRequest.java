package com.learnsyc.appweb.serializers.comentario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteComentarioRequest { //Posiblemente se elimine
    @NotNull(message = "Dato vacio")
    Long id;
}
