package com.zoomix.zoomix.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin
@Configuration
@Log4j2
public class RootController {

    @Value( "${project.info.name}" )
    String name;

    @Value( "${project.info.version}" )
    String version;

    @Value( "${project.info.ambiente}" )
    String ambiente;
    
    @GetMapping("/")
	public ResponseEntity<String> presentacion() {
        log.info("[RootController][presentacion]");
		return new ResponseEntity<String>(name+" "+version+" | "+ambiente, HttpStatus.OK);
	}
}
