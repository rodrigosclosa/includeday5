package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.AvaliacoesDao;
import com.ciandt.includeday6.backend.entity.Agendamentos;
import com.ciandt.includeday6.backend.entity.Avaliacoes;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigogs on 22/08/17.
 */

public class AvaliacaoBO {
    private AvaliacoesDao avaliacoesDao;

    public AvaliacaoBO() {
    }

    public List<Avaliacoes> list(HttpServletRequest req) throws UnauthorizedException {
        LogBO.getInstance().log(req, "Avaliacoes", "list");
        List<Avaliacoes> retorno = null;

        retorno = avaliacoesDao.listByProperty("idUsuario", idUsuario);

        return retorno;
    }

    public Avaliacoes create(HttpServletRequest req, Avaliacoes avaliacoes) throws UnauthorizedException, ConflictException {
        LogBO.getInstance().log(req, "Avaliacoes", "create");

        if(avaliacoes == null) {
            throw new ConflictException("Dados da avaliação não informados.");
        }

        Avaliacoes aval = avaliacoesDao.getByProperty("idAgendamento", avaliacoes.getIdAgendamento());

        if(aval != null) {
            aval.setIdAgendamento(avaliacoes.getIdAgendamento());
            aval.setNota(avaliacoes.getNota());

            avaliacoes = avaliacoesDao.update(aval);
        } else {
            avaliacoes = avaliacoesDao.insert(avaliacoes);
        }

        return avaliacoes;
    }
}
