package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.LogChamadasDao;
import com.ciandt.includeday6.backend.entity.LogChamadas;
import com.ciandt.includeday6.backend.util.DateUtil;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigogs on 22/08/17.
 */

public class LogBO {
    private LogChamadasDao logChamadasDao;
    private static final LogBO ourInstance = new LogBO();

    public static LogBO getInstance() {
        return ourInstance;
    }

    private LogBO() {
        logChamadasDao = new LogChamadasDao();
    }

    public void log(HttpServletRequest request, String endpoint, String metodo) throws UnauthorizedException {
        String numeroGrupo = request.getHeader("numerogrupo");

        if(numeroGrupo == null || numeroGrupo.isEmpty()) {
            throw new UnauthorizedException("Número do grupo não informado no HEADER da chamada.");
        }

        Query.Filter f1 = new Query.FilterPredicate("numeroGrupo", Query.FilterOperator.EQUAL, numeroGrupo);
        Query.Filter f2 = new Query.FilterPredicate("endpoint", Query.FilterOperator.EQUAL, endpoint);
        Query.Filter f3 = new Query.FilterPredicate("metodo", Query.FilterOperator.EQUAL, metodo);

        Query.Filter filter = Query.CompositeFilterOperator.and(f1, f2, f3);

        LogChamadas logChamadas = logChamadasDao.getByFilter(filter);

        if(logChamadas != null) {
            logChamadas.setNumeroGrupo(Integer.valueOf(numeroGrupo));
            logChamadas.setEndpoint(endpoint);
            logChamadas.setMetodo(metodo);
            logChamadas.setDataHora(DateUtil.getBRTTimeZoneDate());

            logChamadasDao.update(logChamadas);
        } else {
            logChamadas = new LogChamadas();
            logChamadas.setNumeroGrupo(Integer.valueOf(numeroGrupo));
            logChamadas.setEndpoint(endpoint);
            logChamadas.setMetodo(metodo);
            logChamadas.setDataHora(DateUtil.getBRTTimeZoneDate());

            logChamadasDao.insert(logChamadas);
        }

    }
}
