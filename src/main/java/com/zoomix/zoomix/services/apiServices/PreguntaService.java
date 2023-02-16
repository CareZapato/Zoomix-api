package com.zoomix.zoomix.services.apiServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.zoomix.zoomix.models.Pregunta;
import com.zoomix.zoomix.repositories.CategoriaRepository;
import com.zoomix.zoomix.repositories.JugadorRepository;
import com.zoomix.zoomix.repositories.PreguntaRepository;

import com.zoomix.zoomix.services.apiServices.CodigoGenerator;
import com.zoomix.zoomix.services.apiServices.DTO.Pregunta.InsertarListaPreguntasDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Pregunta.InsertarPreguntasResponse;
import com.zoomix.zoomix.services.apiServices.DTO.Pregunta.insertarListaPreguntasResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private CodigoGenerator codigoGenerator;

    
    public Iterable<Pregunta> listaPreguntas(){
        return preguntaRepository.findAll();
    }

    public Iterable<Pregunta> findByTexto(String texto){
        return preguntaRepository.findByTexto(texto);
    }

    public InsertarPreguntasResponse insertarPreguntas(Pregunta[] preguntas){
        InsertarPreguntasResponse response = new InsertarPreguntasResponse();
        int insertados = 0;
        int repetidos = 0;
        int errores = 0;

        for(int i=0; i<preguntas.length; i++){
            Iterable<Pregunta> pregs = preguntaRepository.findByTextoAndJugadorJugadorIdAndCategoriaCategoriaId(
                preguntas[i].getTexto(),
                preguntas[i].getJugador().getJugadorId(),
                preguntas[i].getCategoria().getCategoriaId()
            );
            if(Iterables.size(pregs) == 0){
                Pregunta nuevaPregunta = new Pregunta(preguntas[i]);   
                nuevaPregunta.setCategoria(categoriaRepository.findById(preguntas[i].getCategoria().getCategoriaId()).get());
                nuevaPregunta.setJugador(jugadorRepository.findById(preguntas[i].getJugador().getJugadorId()).get());    
                try{
                    preguntaRepository.save(nuevaPregunta);
                    insertados++;
                    log.info("[PreguntaService][insertarPreguntas] - pregunta "+preguntas[i].toString()+" insertado correctamente");
                }catch(Exception e){
                    errores++;
                    log.error("[PreguntaService][insertarPreguntas] - Error al insertar pregunta");
                    response.setError("Error al insertar pregunta "+preguntas[i].toString());
                    return response;
                }
            }else{
                repetidos++;
                log.info("[PreguntaService][insertarPreguntas] - ya existia la pregunta "+preguntas[i].getTexto()+" y no fue necesario insertarlo");
            }
        }
        response.setInsertados(insertados);
        response.setRepetidos(repetidos);
        response.setErrores(errores);
        response.setResponse("Se generaron las transacciones. Revisar valores");
        
        return response;
    }

    public InsertarPreguntasResponse insertarPregunta(Pregunta pregunta){
        InsertarPreguntasResponse response = new InsertarPreguntasResponse();
        List<String> lista = new ArrayList<>();
        boolean insertado = false;
        Iterable<Pregunta> pregs = preguntaRepository.findByTextoAndJugadorJugadorIdAndCategoriaCategoriaId(
            pregunta.getTexto(),
            pregunta.getJugador().getJugadorId(),
            pregunta.getCategoria().getCategoriaId()
        );
        if(Iterables.size(pregs) == 0){
            Pregunta nuevaPregunta = new Pregunta(pregunta);   
            nuevaPregunta.setCategoria(categoriaRepository.findById(pregunta.getCategoria().getCategoriaId()).get());
            nuevaPregunta.setJugador(jugadorRepository.findById(pregunta.getJugador().getJugadorId()).get());    
            nuevaPregunta.setColorOpenAI(pregunta.getColorOpenAI());
            nuevaPregunta.setConcecuencia(pregunta.getConcecuencia());
            nuevaPregunta.setExplicacionColorOpenAI(pregunta.getExplicacionColorOpenAI());
            nuevaPregunta.setLink(codigoGenerator.generarCodigo());
            nuevaPregunta.setRespuesta(pregunta.getRespuesta());
            
            try{
                preguntaRepository.save(nuevaPregunta);
                insertado = true;
                response.setInsertados(1);
                response.setResponse("pregunta "+pregunta.getTexto()+" insertado correctamente");
                log.info("[PreguntaService][insertarPregunta] - pregunta "+pregunta.getTexto()+" insertado correctamente");
            }catch(Exception e){
                log.error("[PreguntaService][insertarPregunta] - Error al insertar pregunta");
                response.setError("Error al insertar pregunta "+pregunta.getTexto());
                return response;
            }
        }else{
            log.info("[PreguntaService][insertarPregunta] - ya existia la pregunta "+pregunta.getTexto()+" y no fue necesario insertarlo");
        }
        response.setResponse(insertado ? "Inserción correcta" : "No hubo inserciones");
        return response;
    }

    public InsertarPreguntasResponse eliminarPreguntas(){
        InsertarPreguntasResponse response = new InsertarPreguntasResponse();
        try{
            Long cant = preguntaRepository.count();
            preguntaRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todas las preguntas de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al eliminar preguntas");
            response.setError(e.toString());
            log.error("[PreguntaService][eliminarPreguntas] - Error al eliminar preguntas");
            return response;
        }
        return response;
    }

    public Pregunta nuevaPregunta(Long categoria) {
        // Obtener todas las preguntas de la categoría especificada
        List<Pregunta> preguntas = preguntaRepository.findByCategoriaCategoriaId(categoria);

        // Si no hay preguntas en la categoría, devolver null
        if (Iterables.size(preguntas) == 0) {
            return null;
        }

        // Si hay preguntas, seleccionar una al azar
        Random random = new Random();
        int indice = random.nextInt(Iterables.size(preguntas));
        return preguntas.get(indice);
    }

    public Pregunta nuevaPregunta() {
        // Obtener todas las preguntas de la categoría especificada
        List<Pregunta> preguntas = preguntaRepository.findAll();

        // Si no hay preguntas en la categoría, devolver null
        if (Iterables.size(preguntas) == 0) {
            return null;
        }

        // Si hay preguntas, seleccionar una al azar
        Random random = new Random();
        int indice = random.nextInt(Iterables.size(preguntas));
        return preguntas.get(indice);
    }

    public insertarListaPreguntasResponse insertarListaPreguntas(ArrayList<InsertarListaPreguntasDTO> listaPreguntasDTO) {
        insertarListaPreguntasResponse response = new insertarListaPreguntasResponse();
        int cat = 0;
        int text = 0;
        for (InsertarListaPreguntasDTO preguntaDTO : listaPreguntasDTO) {
            for (String texto : preguntaDTO.getTexto()) {
                Pregunta pregunta = new Pregunta();
                pregunta.setTexto(texto);
                pregunta.setCategoria(categoriaRepository.findById(preguntaDTO.getCategoria().getCategoriaId()).get());
                pregunta.setJugador(jugadorRepository.findById(preguntaDTO.getJugador().getJugadorId()).get());  
                pregunta.setActivo(true);
                pregunta.setLikes(0);
                try{
                    preguntaRepository.save(pregunta);
                    text++;
                    log.info("[PreguntaService][insertarListaPreguntas] - pregunta "+pregunta.toString()+" insertado correctamente");

                }catch(Exception e){
                    log.error("[PreguntaService][insertarListaPreguntas] - Error al insertar pregunta desde la lista de preguntas");
                    response.setError("Error al insertar pregunta "+pregunta.toString());
                    return response;
                }
            }
            cat++;
        }
        response.setResponse("Se insertan "+text+" preguntas en "+cat+" categorias distintas");
        return response;
    }
}
