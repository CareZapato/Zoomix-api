package com.zoomix.zoomix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomix.zoomix.models.Pregunta;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    
    public Iterable<Pregunta> findByTexto(String texto);

    public Iterable<Pregunta> findByTextoAndJugadorJugadorIdAndCategoriaCategoriaId(String texto, Long jugador, Long categoria);

    public List<Pregunta> findByCategoriaCategoriaId(Long categoria);
}
