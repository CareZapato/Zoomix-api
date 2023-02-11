package com.zoomix.zoomix.services.apiServices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import com.zoomix.zoomix.models.Config;
import com.zoomix.zoomix.models.Pregunta;
import com.zoomix.zoomix.repositories.CategoriaRepository;
import com.zoomix.zoomix.repositories.ConfigRepository;
import com.zoomix.zoomix.repositories.JugadorRepository;
import com.zoomix.zoomix.services.apiServices.DTO.OpenAI.OpenAIRequest;
import com.zoomix.zoomix.services.apiServices.DTO.OpenAI.TextCompletion;

import lombok.extern.log4j.Log4j2;
import com.zoomix.zoomix.utils.Constants;

@Service
@Transactional
@Log4j2
public class OpenAIService{

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional
    public TextCompletion askOpenAI(OpenAIRequest question) {
        log.info("[OpenAIService][askOpenAI]");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getOpenAI_APIKEY());
        HttpEntity<OpenAIRequest> request = new HttpEntity<OpenAIRequest>(question, headers);
        TextCompletion response = restTemplate.postForObject(
            Constants.OPENAI_PREGUNTAS_API_URL, request, TextCompletion.class);

        return response;
    }

    public Pregunta askOpenAICategoria() {
        log.info("[OpenAIService][askOpenAICategoria]");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getOpenAI_APIKEY());
        OpenAIRequest question = new OpenAIRequest(
            Constants.MODEL_TEXT_OPENAI,
            generarConsultaOpenAi(),
            Constants.MAX_TOKENS);
        try{
            HttpEntity<OpenAIRequest> request = new HttpEntity<OpenAIRequest>(question, headers);
            TextCompletion response = restTemplate.postForObject(
                Constants.OPENAI_PREGUNTAS_API_URL, request, TextCompletion.class);

            Pregunta pregunta = new Pregunta();
            pregunta.setActivo(true);
            pregunta.setJugador(jugadorRepository.findByJugadorId(Constants.ID_JUGADOR_OPENAI));
            pregunta.setCategoria(categoriaRepository.findByCategoriaId(Constants.ID_CATEGORIA_OPENAI));
            pregunta.setLikes(0);
            
            log.info("[OpenAIService][askOpenAICategoria] INFO: pregunta:"+response.getChoices().get(0).getText());
            pregunta.setTexto(response.getChoices().get(0).getText().split(";")[1]);
            pregunta.setColorOpenAI(response.getChoices().get(0).getText().split(";")[2]);
            pregunta.setExplicacionColorOpenAI(response.getChoices().get(0).getText().split(";")[3]);

            return pregunta;
        }catch(Exception e){
            log.error("[OpenAIService][askOpenAICategoria] ERROR: Error al consultar por la pregunta en OpenAI");
            return null;
        }
    }

    public String generarConsultaOpenAi(){
        return Constants.CONOCER+" "+Constants.ESTRUCTURA_RESPONSE;
    }

    public String getOpenAI_APIKEY(){
        try{
            ArrayList<Config> configs = configRepository.findByVariable(Constants.OPENAI_KEY);
            if(configs.size()>0){
                return configs.get(0).getValue();
            }else{
                log.error("[OpenAIService][getOpenAI_APIKEY] ERROR: No encontró la variable: OPENAI_KEY");
                return null;
            }
        }catch(Exception e){
            log.error("[OpenAIService][getOpenAI_APIKEY] ERROR: Error al consultar la OPENAI_KEY");
            return null;
        }
    }
}
