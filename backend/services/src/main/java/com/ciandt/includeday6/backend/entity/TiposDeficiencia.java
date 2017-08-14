package com.ciandt.includeday6.backend.entity;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by rodrigogs on 14/08/17.
 */
@Entity
public class TiposDeficiencia extends baseEntity {
    private String descricao;

    public TiposDeficiencia() {
    }

    public TiposDeficiencia(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
