package com.ciandt.includeday6.backend.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by rodrigogs on 14/08/17.
 */
@Entity
public class Usuarios extends baseEntity {
    private String nome;
    @Index
    private String email;
    @Index
    private String senha;

    public Usuarios() {
    }

    public Usuarios(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
