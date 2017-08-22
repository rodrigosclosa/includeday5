package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.TiposDeficienciaDao;
import com.ciandt.includeday6.backend.entity.Estabelecimentos;
import com.ciandt.includeday6.backend.entity.TiposDeficiencia;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigogs on 22/08/17.
 */

public class TiposDeficienciaBO {
    private TiposDeficienciaDao tiposDeficienciaDao;

    public TiposDeficienciaBO() {
        tiposDeficienciaDao = new TiposDeficienciaDao();
    }

    public List<TiposDeficiencia> listAll(HttpServletRequest req) throws UnauthorizedException {
        List<TiposDeficiencia> retorno = null;

        LogBO.getInstance().log(req, "TiposDeficiencia", "listAll");

        retorno = tiposDeficienciaDao.listAll();

        return retorno;
    }
}
