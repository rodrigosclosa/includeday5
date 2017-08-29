package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.AgendamentosDao;
import com.ciandt.includeday6.backend.dao.EstabelecimentosDao;
import com.ciandt.includeday6.backend.dao.UsuariosDao;
import com.ciandt.includeday6.backend.entity.Agendamentos;
import com.ciandt.includeday6.backend.entity.Estabelecimentos;
import com.ciandt.includeday6.backend.entity.Usuarios;
import com.ciandt.includeday6.backend.helper.TokenHelper;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import java.util.List;

import javax.crypto.AEADBadTagException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigogs on 22/08/17.
 */

public class AgendamentosBO {
    private AgendamentosDao agendamentosDao;
    private EstabelecimentosDao estabelecimentosDao;
    private UsuariosDao usuariosDao;

    public AgendamentosBO() {
        agendamentosDao = new AgendamentosDao();
        estabelecimentosDao = new EstabelecimentosDao();
        usuariosDao = new UsuariosDao();
    }

    public List<Agendamentos> list(HttpServletRequest req, Long idUsuario) throws UnauthorizedException {
        LogBO.getInstance().log(req, "Agendamentos", "list");
        List<Agendamentos> retorno = null;

        retorno = agendamentosDao.listByProperty("idUsuario", idUsuario);

        for (Agendamentos agenda: retorno) {
            Usuarios usuario = usuariosDao.getByKey(agenda.getIdUsuario());

            if(usuario != null) {
                agenda.setUsuario(usuario);
            }

            Estabelecimentos estab = estabelecimentosDao.getByKey(agenda.getIdEstabelecimento());

            if(estab != null) {
                agenda.setEstabalecimento(estab);
            }
        }

        return retorno;
    }

    public Agendamentos create(HttpServletRequest req, Agendamentos agendamentos) throws UnauthorizedException, ConflictException {
        LogBO.getInstance().log(req, "Agendamentos", "create");

        if (agendamentos == null) {
            throw new ConflictException("Informações do agendamento não informados.");
        }

        if(estabelecimentosDao.getByKey(agendamentos.getIdEstabelecimento()) == null) {
            throw new ConflictException("Estabelecimento não encontrado para o ID " + agendamentos.getIdEstabelecimento());
        }

        if(usuariosDao.getByKey(agendamentos.getIdUsuario()) == null) {
            throw new ConflictException("Usuário não encontrado para o ID " + agendamentos.getIdUsuario());
        }

        Query.Filter f1 = new Query.FilterPredicate("idUsuario", Query.FilterOperator.EQUAL, agendamentos.getIdUsuario());
        Query.Filter f2 = new Query.FilterPredicate("idEstabelecimento", Query.FilterOperator.EQUAL, agendamentos.getIdEstabelecimento());
        Query.Filter f3 = new Query.FilterPredicate("dataHora", Query.FilterOperator.EQUAL, agendamentos.getDataHora());
        Query.Filter filter = Query.CompositeFilterOperator.and(f1, f2, f3);

        Agendamentos agenda = agendamentosDao.getByFilter(filter);

        if (agenda != null) {
            //Altera os dados
            agenda.setDataHora(agendamentos.getDataHora());
            agenda.setIdEstabelecimento(agendamentos.getIdEstabelecimento());
            agenda.setIdUsuario(agendamentos.getIdUsuario());

            agendamentos = agendamentosDao.update(agenda);
        } else {
            //Cria um novo
            agendamentos = agendamentosDao.insert(agendamentos);
        }

        return agendamentos;
    }

    public Agendamentos setNotaAvaliacao(HttpServletRequest req, Long idAgendamento, Integer notaAvaliacao) throws UnauthorizedException, ConflictException {
        LogBO.getInstance().log(req, "Agendamentos", "setNotaAvaliacao");
        Agendamentos retorno = null;

        if(notaAvaliacao > 5) {
            throw new ConflictException("Nota máxima aceitável: 5");
        }

        retorno = agendamentosDao.getByKey(idAgendamento);

        if(retorno != null) {
            retorno.setNotaAvaliacao(notaAvaliacao);
            retorno = agendamentosDao.update(retorno);
        }

        return retorno;
    }
}
