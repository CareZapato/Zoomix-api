package com.zoomix.zoomix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomix.zoomix.models.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    
    public Iterable<Jugador> findByNombre(String nombre);

    public Iterable<Jugador> findByNick(String nick);
}
