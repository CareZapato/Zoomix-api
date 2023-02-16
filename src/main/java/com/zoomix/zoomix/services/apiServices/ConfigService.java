package com.zoomix.zoomix.services.apiServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.zoomix.zoomix.models.Config;
import com.zoomix.zoomix.repositories.ConfigRepository;
import com.zoomix.zoomix.services.apiServices.DTO.Config.ConfigsJsonDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Config.InsertarConfigsResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    
    public Iterable<Config> listaConfigs(){
        return configRepository.findAll();
    }

    public Iterable<Config> findByVariable(String configEntrada){
        return configRepository.findByVariable(configEntrada);
    }

    public String findValueByVariable(String value){
        log.info("[ConfigService][findValueByVariable] Se obtiene valor de Config "+value);
        return configRepository.findValueByVariable(value).get(0).getValue();
        
    }

    public InsertarConfigsResponse insertarConfigs(Iterable<ConfigsJsonDTO> configsJsonDTO){
        InsertarConfigsResponse response = new InsertarConfigsResponse();
        List<String> lista = new ArrayList<>();
        for(ConfigsJsonDTO config: configsJsonDTO){
            if(Iterables.size(configRepository.findByVariable(config.getVariable())) == 0){
                Config nuevoColor = new Config(config.getVariable(),config.getValue());
                try{
                    configRepository.save(nuevoColor);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(nuevoColor.getVariable());
                    response.setListaConfigs(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar configs");
                    response.setError(e.toString());
                    log.error("[ColorService][insertarConfigs] - Error al insertar configs");
                    return response;
                }
            }else{
                response.setRepetidos(response.getRepetidos() + 1);
            }
        }
        response.setResponse(lista.size() > 0 ? "Inserción correcta" : "No hubo inserciones");
        return response;
    }

    public InsertarConfigsResponse eliminarConfigs(){
        InsertarConfigsResponse response = new InsertarConfigsResponse();
        try{
            Long cant = configRepository.count();
            configRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos los configs de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al eliminar configs");
            response.setError(e.toString());
            log.error("[ColorService][eliminarConfigs] - Error al eliminar configs");
            return response;
        }
        return response;
    }
    

}