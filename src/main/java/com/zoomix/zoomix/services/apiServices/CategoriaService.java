package com.zoomix.zoomix.services.apiServices;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.zoomix.zoomix.repositories.CategoriaRepository;
import com.zoomix.zoomix.repositories.ColorRepository;
import com.zoomix.zoomix.models.Categoria;
import java.util.ArrayList;
import java.util.Date;

import com.zoomix.zoomix.services.apiServices.DTO.Categoria.CategoriasJsonDTO;
import com.zoomix.zoomix.services.apiServices.DTO.Categoria.InsertarCategoriasResponse;


import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ColorRepository colorRepository;

    public Iterable<Categoria> listaCategorias(){
        return categoriaRepository.findAll();
    }

    public Iterable<Categoria> findByNombre(String categoriaEntrada){
        return categoriaRepository.findByNombre(categoriaEntrada);
    }

    public InsertarCategoriasResponse insertarCategorias(Categoria[] categorias){
        InsertarCategoriasResponse response = new InsertarCategoriasResponse();
        List<String> lista = new ArrayList<>();
        for(Categoria categoria: categorias){
            if(Iterables.size(categoriaRepository.findByNombre(categoria.getNombre())) == 0){
                Categoria nuevaCategoria = new Categoria(categoria.getNombre(),categoria.getDescripcion());
                nuevaCategoria.setColor(colorRepository.findById(categoria.getColor().getColorId()).get());
                try{
                    categoriaRepository.save(nuevaCategoria);
                    response.setNuevos(response.getNuevos() + 1);
                    lista.add(categoria.getNombre());
                    response.setListaCategorias(lista);
                }
                catch(Exception e){
                    response.setResponse("Error al insertar categorias");
                    response.setError(e.toString());
                    log.error("[CategoriaService][insertarCategorias] - Error al insertar categorias");
                    return response;
                }
            }else{
                response.setRepetidos(response.getRepetidos() + 1);
            }
        }
        response.setResponse(lista.size() > 0 ? "Inserción correcta" : "No hubo inserciones");
        return response;
    }

    public InsertarCategoriasResponse eliminarCategorias(){
        InsertarCategoriasResponse response = new InsertarCategoriasResponse();
        try{
            Long cant = categoriaRepository.count();
            categoriaRepository.deleteAll();
            response.setEliminados(cant);
            response.setResponse("Se eliminaron todos las categorias de la Base de datos");
        }catch(Exception e){
            response.setResponse("Error al eliminar categorias");
            response.setError(e.toString());
            log.error("[CategoriaService][eliminarCategorias] - Error al eliminar categorias");
            return response;
        }
        return response;
    }
}
