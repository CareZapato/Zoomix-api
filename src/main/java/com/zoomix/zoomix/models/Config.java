package com.zoomix.zoomix.models;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

@Data
@Entity
@Table(name = "config", schema = "public")
public class Config implements Serializable {

    public static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="config_id")
    @GeneratedValue(generator = "config_id_seq")
    @SequenceGenerator(name = "config_id_seq", sequenceName = "public.config_id_seq", allocationSize = 1)
    private Long configId;
    
    @Column(name="VARIABLE")
    private String variable;

    @Column(name="VALUE")
    private String value;

    @Column(name="CREATEDAT")
    private Date createdAt;

    @Column(name="UPDATEDAT")
    private Date updatedAt;

    public Config(String variable, String value){
        this.variable = variable;
        this.value = value;
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public Config(){
    }

}