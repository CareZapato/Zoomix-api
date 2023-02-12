package com.zoomix.zoomix.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categoria", schema = "public")
public class Categoria implements Serializable {

    public static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="categoria_id")
    @GeneratedValue(generator = "categoria_id_seq")
    @SequenceGenerator(name = "categoria_id_seq", sequenceName = "public.categoria_id_seq", allocationSize = 1)
    private Long categoriaId;
    
    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="DESCRIPCION", length=512)
    private String descripcion;

    @Column(name="FORMATO", length=512)
    private String formato;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "color_id", referencedColumnName="color_id")
    private Color color;

    public Categoria(String nombre, String descripcion, String formato){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.formato = formato;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Categoria(){
    }

}