package com.zoomix.zoomix.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomix.zoomix.models.Color;

/*
 * This is the same as the above except it is using the CrudRepository interface instead of the interface above.
 */
@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    
    public Iterable<Color> findByNombre(String nombre);
}
    
