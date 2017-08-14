package com.ciandt.includeday6.backend.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import java.io.Serializable;

/**
 * Created by rodrigosclosa on 23/08/16.
 */
@Entity
public class Token extends baseEntity implements Serializable {
    @Index
    private String codigo;
    private String descricao;
    @Index
    private String token;
    @Index
    private Boolean ativo;

    public Token() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
