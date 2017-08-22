package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.AgendamentosDao;
import com.ciandt.includeday6.backend.entity.Agendamentos;
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

    public AgendamentosBO() {
        agendamentosDao = new AgendamentosDao();
    }

    public List<Agendamentos> list(HttpServletRequest req, Integer idUsuario) throws UnauthorizedException {
        LogBO.getInstance().log(req, "Agendamentos", "list");
        List<Agendamentos> retorno = null;

        retorno = agendamentosDao.listByProperty("idUsuario", idUsuario);

        return retorno;
    }

    public Agendamentos create(HttpServletRequest req, Agendamentos agendamentos) throws UnauthorizedException, ConflictException {
        LogBO.getInstance().log(req, "Agendamentos", "create");

        if (agendamentos == null) {
            throw new ConflictException("Informações do agendamento não informados.");
        }

        Query.Filter f1 = new Query.FilterPredicate("idUsuario", Query.FilterOperator.EQUAL, agendamentos.getIdUsuario());
        Query.Filter f2 = new Query.FilterPredicate("idEstabelecimento", Query.FilterOperator.EQUAL, agendamentos.getIdEstabelecimento());
        Query.Filter f3 = new Query.FilterPredicate("dataHora", Query.FilterOperator.EQUAL, agendamentos.getDataHora());
        Query.Filter filter = Query.CompositeFilterOperator.and(f1, f2, f3);

        Agendamentos agenda = agendamentosDao.getByFilter(filter);

        if(agenda != null) {
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
}
