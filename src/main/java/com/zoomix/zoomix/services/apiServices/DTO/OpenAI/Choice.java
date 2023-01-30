package com.zoomix.zoomix.services.apiServices.DTO.OpenAI;

import lombok.Data;

@Data
public class Choice {
    private String text;
    private int index;
    private Object logprobs;
    private String finish_reason;
}
