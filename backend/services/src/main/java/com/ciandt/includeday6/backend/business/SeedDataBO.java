package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.TokenDao;
import com.ciandt.includeday6.backend.entity.Token;
import com.ciandt.includeday6.backend.helper.TokenHelper;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 28/12/16.
 */

public class SeedDataBO {
    public SeedDataBO() {
    }

    public void seedBaseData(HttpServletRequest request) throws UnauthorizedException {

        //Tokens de Acesso
        Token token = TokenDao.getInstance().getByProperty("codigo", "FRONTEND");

        if (token == null) {
            token = new Token();
            token.setDescricao("Frontend da Aplicação");
            token.setCodigo("FRONTEND");
            token.setToken("0ABCCC629E2C4DAAB2DE36A042E3BB74");
            token.setAtivo(true);

            TokenDao.getInstance().insert(token);
        }

        token = TokenDao.getInstance().getByProperty("codigo", "POSTMAN");

        if (token == null) {
            token = new Token();
            token.setDescricao("Postman Testes");
            token.setCodigo("POSTMAN");
            token.setToken("1DD0B7C1D6B9415EBC783CA432F60682");
            token.setAtivo(true);

            TokenDao.getInstance().insert(token);
        }

        if(TokenHelper.getInstance().tokenValido(request)) {


        }
    }
}
