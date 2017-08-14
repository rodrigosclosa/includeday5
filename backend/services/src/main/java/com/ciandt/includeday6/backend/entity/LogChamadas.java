package com.ciandt.includeday6.backend.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

/**
 * Created by rodrigogs on 14/08/17.
 */
@Entity
public class LogChamadas extends baseEntity {
    @Index
    private Integer numeroGrupo;
    @Index
    private String endpoint;
    @Index
    private String metodo;
    private Date dataHora;

    public LogChamadas() {
    }

    public LogChamadas(Integer numeroGrupo, String endpoint, String metodo, Date dataHora) {
        this.numeroGrupo = numeroGrupo;
        this.endpoint = endpoint;
        this.metodo = metodo;
        this.dataHora = dataHora;
    }

    public Integer getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(Integer numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
}
