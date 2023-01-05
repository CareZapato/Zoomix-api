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

import com.zoomix.zoomix.models.Color;
import com.zoomix.zoomix.services.apiServices.ColorService;
import com.zoomix.zoomix.services.apiServices.DTO.Color.ColoresJsonDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Color.InsertarColoresResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/colores")
	public ResponseEntity<Iterable<Color>> colores() {
		log.info("[ColorController][colores]");
		return new ResponseEntity<Iterable<Color>>(colorService.listaColores(), HttpStatus.OK);
	}

	@GetMapping("/colores/{colorNombre}")
	public ResponseEntity<Iterable<Color>> colorNombre(
		@PathVariable String colorNombre
	) {
		log.info("[ColorController][colorNombre]");
		return new ResponseEntity<Iterable<Color>>(colorService.findByNombre(colorNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarColores")
	public ResponseEntity<InsertarColoresResponse> insertarColores(
		@RequestBody Iterable<ColoresJsonDTO> coloresJsonDTO
	) {
		log.info("[ColorController][insertarColores]");
		return new ResponseEntity<InsertarColoresResponse>(colorService.insertarColores(coloresJsonDTO), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarColores")
	public ResponseEntity<InsertarColoresResponse> eliminarColores() {
		log.info("[ColorController][eliminarColores]");
		return new ResponseEntity<InsertarColoresResponse>(colorService.eliminarColores(), HttpStatus.OK);
	}
}
