package com.zoomix.zoomix.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pregunta", schema = "public")
public class Pregunta implements Serializable {

    public static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="pregunta_id")
    @GeneratedValue(generator = "pregunta_id_seq")
    @SequenceGenerator(name = "pregunta_id_seq", sequenceName = "public.pregunta_id_seq", allocationSize = 1)
    private Long preguntaId;
    
    @Column(name="TEXTO")
    private String texto;

    @Column(name="LIKES")
    private int likes;

    @Column(name="ACTIVO")
    private Boolean activo;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "categoria_id", referencedColumnName="categoria_id")
    private Categoria categoria;

    @Column(name="COLOROPENAI")
    private String colorOpenAI;

    @Column(name="EXPLICACIONCOLOROPENAI")
    private String explicacionColorOpenAI;

    @Column(name="CONSECUENCIA")
    private String concecuencia;

    @Column(name="RESPUESTA")
    private String respuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "jugador_id", referencedColumnName="jugador_id")
    private Jugador jugador;

    public Pregunta(Pregunta pregunta){
        this.texto = pregunta.getTexto();
        this.likes = pregunta.getLikes();
        this.activo = true;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Pregunta(){
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

}