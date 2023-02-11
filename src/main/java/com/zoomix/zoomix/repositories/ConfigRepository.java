package com.zoomix.zoomix.repositories;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoomix.zoomix.models.Config;

/*
 * This is the same as the above except it is using the CrudRepository interface instead of the interface above.
 */
@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
    
    public ArrayList<Config> findByVariable(String nombre);
    
}
    
