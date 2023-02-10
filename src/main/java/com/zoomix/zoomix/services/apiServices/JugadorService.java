package com.zoomix.zoomix.services.apiServices;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.zoomix.zoomix.repositories.JugadorRepository;
import com.zoomix.zoomix.models.Jugador;
import java.util.ArrayList;
import com.zoomix.zoomix.services.apiServices.DTO.Jugador.InsertarJugadoresResponse;


import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class JugadorService{

    @Autowired
    private JugadorRepository jugadorRepository;

    public Iterable<Jugador> listaJugadores(){
        return jugadorRepository.findAll();
    }

    public Iterable<Jugador> findByNick(String nick){
        return jugadorRepository.findByNick(nick);
    }

    public Iterable<Jugador> findByNombre(String nick){
        return jugadorRepository.findByNombre(nick);
    }

    public InsertarJugadoresResponse insertarJugadores(Jugador[] jugadores){
        InsertarJugadoresResponse response = new InsertarJugadoresResponse();
        List<String> lista = new ArrayList<>();
        for(Jugador jugador: jugadores){
            if(Iterables.size(jugadorRepository.findByNick(jugador.getNick())) == 0){
                Jugador nuevoJugador = new Jugador(jugador.getNombre(),jugador.getNick());
                try{
                    jugadorRepository.save(nuevoJugador);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(jugador.getNick());
                    response.setListaJugadores(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar jugadores");
                    response.setError(e.toString());
                    log.error("[JugadorService][insertarJugadores] - Error al insertar jugadores");
                    return response;
                }
            }else{
                response.setRepetidos(response.getRepetidos() + 1);
            }
        }
        response.setResponse(lista.size() > 0 ? "Inserción correcta" : "No hubo inserciones");
        return response;
    }

    public InsertarJugadoresResponse eliminarJugadores(){
        InsertarJugadoresResponse response = new InsertarJugadoresResponse();
        try{
            Long cant = jugadorRepository.count();
            jugadorRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todas las jugadors de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al eliminar jugadors");
            response.setError(e.toString());
            log.error("[JugadorService][eliminarJugadors] - Error al eliminar jugadors");
            return response;
        }
        return response;
    }
}
