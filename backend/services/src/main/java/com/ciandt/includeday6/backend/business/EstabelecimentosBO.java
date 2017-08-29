package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.EstabelecimentosDao;
import com.ciandt.includeday6.backend.dao.LogChamadasDao;
import com.ciandt.includeday6.backend.entity.Estabelecimentos;
import com.ciandt.includeday6.backend.helper.TokenHelper;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigogs on 22/08/17.
 */

public class EstabelecimentosBO {
    private EstabelecimentosDao estabelecimentosDao;

    public EstabelecimentosBO() {
        estabelecimentosDao = new EstabelecimentosDao();
    }

    public List<Estabelecimentos> listAll(HttpServletRequest req, String nome) throws UnauthorizedException {
        LogBO.getInstance().log(req, "Estabelecimentos", "listAll");

        List<Estabelecimentos> retorno = null;

        if (nome != null && !nome.isEmpty()) {
            retorno = estabelecimentosDao.listByStartWith("nome", nome);
        } else {
            retorno = estabelecimentosDao.listAll();
        }

        return retorno;
    }
}
