package com.zoomix.zoomix.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoomix.zoomix.models.Pregunta;
import com.zoomix.zoomix.services.apiServices.PreguntaService;
import com.zoomix.zoomix.services.apiServices.DTO.Pregunta.InsertarListaPreguntasDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Pregunta.InsertarPreguntasResponse;
import com.zoomix.zoomix.services.apiServices.DTO.Pregunta.insertarListaPreguntasResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/pregunta")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class PreguntaController {

    private final PreguntaService preguntaService;

    @GetMapping("/preguntas")
	public ResponseEntity<Iterable<Pregunta>> preguntas() {
		log.info("[PreguntaController][preguntas]");
		return new ResponseEntity<Iterable<Pregunta>>(preguntaService.listaPreguntas(), HttpStatus.OK);
	}

	@GetMapping("/preguntas/{preguntaTexto}")
	public ResponseEntity<Iterable<Pregunta>> preguntaTexto(
		@PathVariable String preguntaTexto
	) {
		log.info("[PreguntaController][PreguntaTexto]");
		return new ResponseEntity<Iterable<Pregunta>>(preguntaService.findByTexto(preguntaTexto), HttpStatus.OK);
	}

	@GetMapping("/nuevaPregunta/{categoriaId}")
	public ResponseEntity<Pregunta> nuevaPregunta(
		@PathVariable Long categoriaId
	) {
		log.info("[PreguntaController][PreguntaTexto]");
		return new ResponseEntity<Pregunta>(preguntaService.nuevaPregunta(categoriaId), HttpStatus.OK);
	}

	@GetMapping("/nuevaPregunta")
	public ResponseEntity<Pregunta> nuevaPregunta() {
		log.info("[PreguntaController][PreguntaTexto]");
		return new ResponseEntity<Pregunta>(preguntaService.nuevaPregunta(), HttpStatus.OK);
	}

	@PostMapping("/insertarPreguntas")
	public ResponseEntity<InsertarPreguntasResponse> insertarPreguntas(
		@RequestBody Pregunta[] preguntas
	) {
		log.info("[PreguntaController][insertarPreguntas]");
		return new ResponseEntity<InsertarPreguntasResponse>(preguntaService.insertarPreguntas(preguntas), HttpStatus.OK);
	}

	@PostMapping("/insertarPregunta")
	public ResponseEntity<InsertarPreguntasResponse> insertarPregunta(
		@RequestBody Pregunta pregunta
	) {
		log.info("[PreguntaController][insertarPregunta]");
		return new ResponseEntity<InsertarPreguntasResponse>(preguntaService.insertarPregunta(pregunta), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarPreguntas")
	public ResponseEntity<InsertarPreguntasResponse> eliminarPreguntas() {
		log.info("[PreguntaController][eliminarPreguntas]");
		return new ResponseEntity<InsertarPreguntasResponse>(preguntaService.eliminarPreguntas(), HttpStatus.OK);
	}

	@PostMapping("/insertarListaPreguntas")
	public ResponseEntity<insertarListaPreguntasResponse> insertarListaPreguntas(
		@RequestBody ArrayList<InsertarListaPreguntasDTO> listaPreguntas
	) {
		log.info("[PreguntaController][insertarListaPreguntas]");
		return new ResponseEntity<insertarListaPreguntasResponse>(preguntaService.insertarListaPreguntas(listaPreguntas), HttpStatus.OK);
	}
}
