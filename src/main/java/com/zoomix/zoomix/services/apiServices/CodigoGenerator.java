package com.zoomix.zoomix.services.apiServices;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zoomix.zoomix.models.Pregunta;
import com.zoomix.zoomix.repositories.PreguntaRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class CodigoGenerator {
    private final int MIN_LARGO = 9;
    private final int MAX_LARGO = 12;
    private final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private final Random RANDOM = new Random();

    @Autowired
    private PreguntaRepository preguntaRepository;

    public String generarCodigo() {
        log.info("[CodigoGenerator][generarCodigo] - Inicio funcion generarCodigo");
        boolean existe = true;
        String codigo = "";
        int largo = RANDOM.nextInt(MAX_LARGO - MIN_LARGO + 1) + MIN_LARGO;
        StringBuilder sb = new StringBuilder();
        while(existe){
            int cantidad = 0;
            for (int i = 0; i < largo; i++) {
                char c = CHARS[RANDOM.nextInt(CHARS.length)];
                sb.append(c);
            }
            codigo = sb.toString();
            try{
                Iterable<Pregunta> preguntas = preguntaRepository.findByLink(codigo);
                for(Pregunta pregunta: preguntas){
                    cantidad++;
                }
                if(cantidad==0){
                    log.info("[CodigoGenerator][generarCodigo] - link no existe, se agrega");
                    existe=false;
                }else{
                    log.info("[CodigoGenerator][generarCodigo] - link existe, se vuelve a generar");
                }
            }catch(Exception e){
                log.error("[CodigoGenerator][generarCodigo] - Error al buscar el link en la bd");
            }
        }
        return codigo;
    }
}


