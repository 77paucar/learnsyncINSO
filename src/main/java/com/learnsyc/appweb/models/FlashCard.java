package com.learnsyc.appweb.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "flashcards")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FlashCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_flashcard")
    private Long idFlashcard;

    @Column(name = "concepto", nullable = false)
    private String concepto;

    @Column(name = "respuesta", nullable = false)
    private String respuesta;
    @JoinColumns({
            @JoinColumn(name="id_mazo", referencedColumnName="id_mazo")
    })
    @ManyToOne
    private Mazo mazo;
}
