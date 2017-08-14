package com.ciandt.includeday6.backend.entity;

import com.googlecode.objectify.annotation.Entity;

/**
 * Created by rodrigogs on 14/08/17.
 */
@Entity
public class Estabalecimentos extends baseEntity {
    private String nome;
    private String descricao;
    private String urlFoto;
    private String cidade;
    private String estado;
    private String nomeGuiaQualificado;

    public Estabalecimentos() {
    }

    public Estabalecimentos(String nome, String descricao, String urlFoto, String cidade, String estado, String nomeGuiaQualificado) {
        this.nome = nome;
        this.descricao = descricao;
        this.urlFoto = urlFoto;
        this.cidade = cidade;
        this.estado = estado;
        this.nomeGuiaQualificado = nomeGuiaQualificado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomeGuiaQualificado() {
        return nomeGuiaQualificado;
    }

    public void setNomeGuiaQualificado(String nomeGuiaQualificado) {
        this.nomeGuiaQualificado = nomeGuiaQualificado;
    }
}
