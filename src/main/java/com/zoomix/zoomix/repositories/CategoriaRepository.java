package com.zoomix.zoomix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomix.zoomix.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    public Iterable<Categoria> findByNombre(String nombre);

    public String findDescripcionByNombre(String nombre);

    public Categoria findByCategoriaId(Long categoriaId);
}
