package com.zoomix.zoomix.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "color", schema = "public")
public class Color implements Serializable {

    public static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="color_id")
    @GeneratedValue(generator = "color_id_seq")
    @SequenceGenerator(name = "color_id_seq", sequenceName = "public.color_id_seq", allocationSize = 1)
    private Long colorId;
    
    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION")
    private String descripcion;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    public Color(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Color(){
    }

}