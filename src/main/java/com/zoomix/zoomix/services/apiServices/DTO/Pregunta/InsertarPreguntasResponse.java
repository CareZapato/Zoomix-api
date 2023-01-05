package com.zoomix.zoomix.services.apiServices.DTO.Pregunta;

import java.util.List;

import lombok.Data;

@Data
public class InsertarPreguntasResponse {
    String response;
    String error;
    List<String> listaPreguntas;
    int nuevos;
    int repetidos;
    Long eliminados;
}