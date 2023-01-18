package com.zoomix.zoomix.services.apiServices.DTO.Pregunta;

import com.zoomix.zoomix.models.Categoria;
import com.zoomix.zoomix.models.Jugador;

import lombok.Data;

@Data
public class InsertarListaPreguntasDTO {
    String[] texto;
    Categoria categoria;
    Jugador jugador;
}
