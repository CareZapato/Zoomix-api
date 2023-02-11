package com.zoomix.zoomix.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomix.zoomix.models.Pregunta;
import com.zoomix.zoomix.services.apiServices.OpenAIService;
import com.zoomix.zoomix.services.apiServices.DTO.OpenAI.OpenAIRequest;
import com.zoomix.zoomix.services.apiServices.DTO.OpenAI.TextCompletion;

import lombok.extern.log4j.Log4j2;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/openai")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class OpenAIController {

    @Autowired
    private final OpenAIService openAIService;

    @PostMapping("/askOpenAI")
    public ResponseEntity<TextCompletion> askOpenAI(@RequestBody OpenAIRequest question) {
        log.info("[OpenAIController][askOpenAI]");
        return new ResponseEntity<TextCompletion>(openAIService.askOpenAI(question), HttpStatus.OK);
    }

    @GetMapping("/askOpenAICategoria/{categoriaId}")
    public ResponseEntity<Pregunta> askOpenAICategoria(
        @PathVariable Long categoriaId
    ) {
        log.info("[OpenAIController][askOpenAICategoria]");
        return new ResponseEntity<Pregunta>(openAIService.askOpenAICategoria(categoriaId), HttpStatus.OK);
    }
}
