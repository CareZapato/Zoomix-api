package com.zoomix.zoomix.services.apiServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.zoomix.zoomix.models.Color;
import com.zoomix.zoomix.repositories.ColorRepository;
import com.zoomix.zoomix.services.apiServices.DTO.Color.ColoresJsonDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Color.InsertarColoresResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    
    public Iterable<Color> listaColores(){
        return colorRepository.findAll();
    }

    public Iterable<Color> findByNombre(String colorEntrada){
        return colorRepository.findByNombre(colorEntrada);
    }

    public InsertarColoresResponse insertarColores(Iterable<ColoresJsonDTO> coloresJsonDTO){
        InsertarColoresResponse response = new InsertarColoresResponse();
        List<String> lista = new ArrayList<>();
        for(ColoresJsonDTO color: coloresJsonDTO){
            if(Iterables.size(colorRepository.findByNombre(color.getNombre())) == 0){
                Color nuevoColor = new Color(color.getNombre(),color.getDescripcion());
                try{
                    colorRepository.save(nuevoColor);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(nuevoColor.getNombre());
                    response.setListaColores(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar colores");
                    response.setError(e.toString());
                    log.error("[ColorService][insertarColores] - Error al insertar colores");
                    return response;
                }
            }else{
                response.setRepetidos(response.getRepetidos() + 1);
            }
        }
        response.setResponse(lista.size() > 0 ? "Inserción correcta" : "No hubo inserciones");
        return response;
    }

    public InsertarColoresResponse eliminarColores(){
        InsertarColoresResponse response = new InsertarColoresResponse();
        try{
            Long cant = colorRepository.count();
            colorRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos los colores de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al eliminar colores");
            response.setError(e.toString());
            log.error("[ColorService][eliminarColores] - Error al eliminar colores");
            return response;
        }
        return response;
    }
}