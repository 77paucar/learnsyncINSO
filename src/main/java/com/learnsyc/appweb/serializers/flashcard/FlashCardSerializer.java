package com.learnsyc.appweb.serializers.flashcard;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlashCardSerializer {
    private String concepto;
    private String respuesta;
    private Long idMazo;
}
