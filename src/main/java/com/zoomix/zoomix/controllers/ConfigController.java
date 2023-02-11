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

import com.zoomix.zoomix.models.Config;
import com.zoomix.zoomix.services.apiServices.ConfigService;
import com.zoomix.zoomix.services.apiServices.DTO.Config.ConfigsJsonDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Config.InsertarConfigsResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class ConfigController {

	@Autowired
    private final ConfigService configService;

    @GetMapping("/configs")
	public ResponseEntity<Iterable<Config>> configs() {
		log.info("[ConfigController][configs]");
		return new ResponseEntity<Iterable<Config>>(configService.listaConfigs(), HttpStatus.OK);
	}

	@GetMapping("/configs/{configVariable}")
	public ResponseEntity<Iterable<Config>> configNombre(
		@PathVariable String configVariable
	) {
		log.info("[ConfigController][configVariable]");
		return new ResponseEntity<Iterable<Config>>(configService.findByVariable(configVariable), HttpStatus.OK);
	}

	@PostMapping("/insertarConfigs")
	public ResponseEntity<InsertarConfigsResponse> insertarConfigs(
		@RequestBody Iterable<ConfigsJsonDTO> configsJsonDTO
	) {
		log.info("[ConfigController][insertarConfigs]");
		return new ResponseEntity<InsertarConfigsResponse>(configService.insertarConfigs(configsJsonDTO), HttpStatus.OK);
	}

	@DeleteMapping("/eliminarConfigs")
	public ResponseEntity<InsertarConfigsResponse> eliminarConfigs() {
		log.info("[ConfigController][eliminarConfigs]");
		return new ResponseEntity<InsertarConfigsResponse>(configService.eliminarConfigs(), HttpStatus.OK);
	}
}
