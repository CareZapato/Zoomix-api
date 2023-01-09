package com.zoomix.zoomix.services.apiServices.DTO.Pregunta;

import java.util.List;

import lombok.Data;

@Data
public class InsertarPreguntasResponse {
    String response;
    String error;
    int repetidos;
    int insertados;
    int errores;
    Long eliminados;
}