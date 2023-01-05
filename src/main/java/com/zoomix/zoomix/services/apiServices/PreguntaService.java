package com.zoomix.zoomix.services.apiServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.zoomix.zoomix.models.Pregunta;
import com.zoomix.zoomix.repositories.PreguntaRepository;
import com.zoomix.zoomix.services.apiServices.DTO.Pregunta.PreguntasJsonDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Jugador.JugadoresJsonDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Pregunta.InsertarPreguntasResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    
    public Iterable<Pregunta> listaPreguntas(){
        return preguntaRepository.findAll();
    }

    public Iterable<Pregunta> findByTexto(String texto){
        return preguntaRepository.findByTexto(texto);
    }

    public InsertarPreguntasResponse insertarPreguntas(Iterable<PreguntasJsonDTO> preguntasJsonDTO){
        InsertarPreguntasResponse response = new InsertarPreguntasResponse();
        List<String> lista = new ArrayList<>();
        for(PreguntasJsonDTO pregunta: preguntasJsonDTO){
            if(Iterables.size(preguntaRepository.findByTexto(pregunta.getTexto())) == 0){
                Pregunta nuevaPregunta = new Pregunta(pregunta.getTexto());
                try{
                    preguntaRepository.save(nuevaPregunta);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(nuevaPregunta.getTexto());
                    response.setListaPreguntas(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar preguntas");
                    response.setError(e.toString());
                    log.error("[PreguntaService][insertarPreguntas] - Error al insertar preguntas");
                    return response;
                }
            }else{
                response.setRepetidos(response.getRepetidos() + 1);
            }
        }
        response.setResponse(lista.size() > 0 ? "Inserción correcta" : "No hubo inserciones");
        return response;
    }

    public InsertarPreguntasResponse eliminarPreguntas(){
        InsertarPreguntasResponse response = new InsertarPreguntasResponse();
        try{
            Long cant = preguntaRepository.count();
            preguntaRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos los preguntas de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al eliminar preguntas");
            response.setError(e.toString());
            log.error("[PreguntaService][eliminarPreguntas] - Error al eliminar preguntas");
            return response;
        }
        return response;
    }
}
