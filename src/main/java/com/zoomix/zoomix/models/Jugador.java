package com.zoomix.zoomix.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "jugador", schema = "public")
public class Jugador implements Serializable {

    public static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="jugador_id")
    @GeneratedValue(generator = "jugador_id_seq")
    @SequenceGenerator(name = "jugador_id_seq", sequenceName = "public.jugador_id_seq", allocationSize = 1)
    private Long colorId;
    
    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="NICK")
    private String nick;

    @Column(name="facebook_id")
    private String facebook_id;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    public Jugador(String nombre, String nick){
        this.nombre = nombre;
        this.nick = nick;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Jugador(){
    }

}