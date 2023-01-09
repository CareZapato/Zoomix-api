package com.zoomix.zoomix.controllers;

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

import com.zoomix.zoomix.models.Jugador;
import com.zoomix.zoomix.services.apiServices.JugadorService;
import com.zoomix.zoomix.services.apiServices.DTO.Jugador.InsertarJugadoresResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/jugador")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class JugadorController {

    private final JugadorService jugadorService;

    @GetMapping("/jugadores")
	public ResponseEntity<Iterable<Jugador>> jugadores() {
		log.info("[JugadorController][jugadors]");
		return new ResponseEntity<Iterable<Jugador>>(jugadorService.listaJugadores(), HttpStatus.OK);
	}

	@GetMapping("/jugadores/{jugadorTexto}")
	public ResponseEntity<Iterable<Jugador>> jugadorNombre(
		@PathVariable String jugadorNombre
	) {
		log.info("[JugadorController][JugadorTexto]");
		return new ResponseEntity<Iterable<Jugador>>(jugadorService.findByNombre(jugadorNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarJugadores")
	public ResponseEntity<InsertarJugadoresResponse> insertarJugadores(
		@RequestBody Jugador[] jugadores
	) {
		log.info("[JugadorController][insertarJugadores]");
		return new ResponseEntity<InsertarJugadoresResponse>(jugadorService.insertarJugadores(jugadores), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarJugadores")
	public ResponseEntity<InsertarJugadoresResponse> eliminarJugadors() {
		log.info("[JugadorController][eliminarJugadores]");
		return new ResponseEntity<InsertarJugadoresResponse>(jugadorService.eliminarJugadores(), HttpStatus.OK);
	}
}
