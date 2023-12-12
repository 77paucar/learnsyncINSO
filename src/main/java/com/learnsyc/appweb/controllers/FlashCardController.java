package com.learnsyc.appweb.controllers;

import com.learnsyc.appweb.models.FlashCard;
import com.learnsyc.appweb.serializers.flashcard.FlashCardSerializer;
import com.learnsyc.appweb.serializers.flashcard.SaveFlashCardRequest;
import com.learnsyc.appweb.services.FlashCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flashcard")
@CrossOrigin(origins = "https://velvety-dusk-4569ef.netlify.app")
public class FlashCardController {
    @Autowired
    FlashCardService flashCardService;

    @GetMapping("/listar/")
    public List<FlashCardSerializer> listarFlashCard(){
        return flashCardService.listarFlashCards().stream().map((it)-> flashCardService.retornarFlashCard(it)).toList();
    }

    @PostMapping("/")
    public FlashCard crearFlashCard(@Valid @RequestBody SaveFlashCardRequest request){
        return flashCardService.guardarFlashCard(request);
    }
}
