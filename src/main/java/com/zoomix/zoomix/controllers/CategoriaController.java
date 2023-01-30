package com.zoomix.zoomix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.zoomix.zoomix.models.Categoria;
import com.zoomix.zoomix.services.apiServices.CategoriaService;
import com.zoomix.zoomix.services.apiServices.DTO.Categoria.CategoriasJsonDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Categoria.InsertarCategoriasResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class CategoriaController {

	@Autowired
    private final CategoriaService categoriaService;

    @GetMapping("/categorias")
	public ResponseEntity<Iterable<Categoria>> categorias() {
		log.info("[CategoriaController][categorias]");
		return new ResponseEntity<Iterable<Categoria>>(categoriaService.listaCategorias(), HttpStatus.OK);
	}

	@GetMapping("/categorias/{categoriaNombre}")
	public ResponseEntity<Iterable<Categoria>> categoriaNombre(
		@PathVariable String categoriaNombre
	) {
		log.info("[CategoriaController][categoriaNombre]");
		return new ResponseEntity<Iterable<Categoria>>(categoriaService.findByNombre(categoriaNombre), HttpStatus.OK);
	}

	@PostMapping("/insertarCategorias")
	public ResponseEntity<InsertarCategoriasResponse> insertarCategorias(
		@RequestBody Categoria[] categorias
	) {
		log.info("[CategoriaController][insertarCategorias]");
		return new ResponseEntity<InsertarCategoriasResponse>(categoriaService.insertarCategorias(categorias), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarCategorias")
	public ResponseEntity<InsertarCategoriasResponse> eliminarCategorias() {
		log.info("[CategoriaController][eliminarCategorias]");
		return new ResponseEntity<InsertarCategoriasResponse>(categoriaService.eliminarCategorias(), HttpStatus.OK);
	}
}
