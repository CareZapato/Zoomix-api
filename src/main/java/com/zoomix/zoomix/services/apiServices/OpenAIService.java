package com.zoomix.zoomix.services.apiServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import com.zoomix.zoomix.services.apiServices.DTO.OpenAI.OpenAIRequest;
import com.zoomix.zoomix.services.apiServices.DTO.OpenAI.TextCompletion;

import lombok.extern.log4j.Log4j2;
import com.zoomix.zoomix.utils.Constants;

@Service
@Transactional
@Log4j2
public class OpenAIService{

    @Value( "${openai.api.key}" )
    String API_KEY_OPENAI;
    
    public TextCompletion askOpenAI(OpenAIRequest question) {
        log.info("[OpenAIService][askOpenAI]");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY_OPENAI);

        HttpEntity<OpenAIRequest> request = new HttpEntity<OpenAIRequest>(question, headers);
        TextCompletion response = restTemplate.postForObject(
                "https://api.openai.com/v1/completions", request, TextCompletion.class);

        return response;
    }

    public TextCompletion askOpenAICategoria() {
        log.info("[OpenAIService][askOpenAICategoria]");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(API_KEY_OPENAI);

        OpenAIRequest question = new OpenAIRequest();
        question.setMax_tokens(100);
        question.setModel("text-davinci-003");
        question.setPrompt(Constants.conocer+" "+Constants.estructura_response);

        HttpEntity<OpenAIRequest> request = new HttpEntity<OpenAIRequest>(question, headers);
        TextCompletion response = restTemplate.postForObject(
                "https://api.openai.com/v1/completions", request, TextCompletion.class);
        return response;
    }
}
