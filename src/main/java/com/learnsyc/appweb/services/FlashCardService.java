package com.learnsyc.appweb.services;

import com.learnsyc.appweb.models.FlashCard;
import com.learnsyc.appweb.models.Mazo;
import com.learnsyc.appweb.repositories.FlashCardRepository;
import com.learnsyc.appweb.serializers.flashcard.FlashCardSerializer;
import com.learnsyc.appweb.serializers.flashcard.SaveFlashCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashCardService {
    @Autowired
    FlashCardRepository flashCardRepository;
    @Autowired MazoService mazoService;
    public List<FlashCard> listarFlashCards() {
        return flashCardRepository.findAll();
    }

    public FlashCard guardarFlashCard(SaveFlashCardRequest request) {
        Mazo mazo = mazoService.encontrarMazo(request.getId());
        FlashCard flashCard = new FlashCard(null, request.getConcepto(), request.getRespuesta(), mazo);
        return flashCardRepository.save(flashCard);
    }

    public FlashCardSerializer retornarFlashCard(FlashCard flashCard){
        return new FlashCardSerializer(flashCard.getConcepto(), flashCard.getRespuesta(), flashCard.getMazo().getIdMazo());
    }
}
