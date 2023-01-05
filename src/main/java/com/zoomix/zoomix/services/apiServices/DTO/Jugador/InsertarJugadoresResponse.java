package com.zoomix.zoomix.services.apiServices.DTO.Jugador;

import java.util.List;

import lombok.Data;

@Data
public class InsertarJugadoresResponse {
    String response;
    String error;
    List<String> listaJugadores;
    int nuevos;
    int repetidos;
    Long eliminados;
}