package com.zoomix.zoomix.services.apiServices.DTO.OpenAI;

import java.util.List;

import lombok.Data;

@Data
public class TextCompletion {
    private String id;
    private String object;
    private int created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
}
