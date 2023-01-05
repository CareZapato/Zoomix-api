package com.zoomix.zoomix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomix.zoomix.models.Pregunta;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    
    public Iterable<Pregunta> findByTexto(String texto);
}
