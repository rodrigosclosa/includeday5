package com.ciandt.includeday6.backend.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;

/**
 * Created by rodrigogs on 14/08/17.
 */
@Entity
public class Agendamentos extends baseEntity {
    @Index
    private Long idUsuario;
    @Ignore
    private Usuarios usuario;
    @Index
    private Long idEstabelecimento;
    @Ignore
    private Estabalecimentos estabalecimento;
    private Date dataHora;

    public Agendamentos() {
    }

    public Agendamentos(Long idUsuario, Long idEstabelecimento, Date dataHora) {
        this.idUsuario = idUsuario;
        this.idEstabelecimento = idEstabelecimento;
        this.dataHora = dataHora;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(Long idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Estabalecimentos getEstabalecimento() {
        return estabalecimento;
    }

    public void setEstabalecimento(Estabalecimentos estabalecimento) {
        this.estabalecimento = estabalecimento;
    }
}
