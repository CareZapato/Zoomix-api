package com.zoomix.zoomix.services.apiServices.DTO.Config;

import java.util.List;

import lombok.Data;

@Data
public class InsertarConfigsResponse {
    String response;
    String error;
    List<String> listaConfigs;
    int nuevos;
    int repetidos;
    Long eliminados;
}