package com.ciandt.includeday6.backend.helper;

import com.ciandt.includeday6.backend.business.TokenBO;
import com.ciandt.includeday6.backend.entity.Token;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class TokenHelper {
    private static TokenBO tokensService;
    private static TokenHelper ourInstance = new TokenHelper();

    public static TokenHelper getInstance() {
        return ourInstance;
    }

    private TokenHelper() {
        tokensService = new TokenBO();
    }

    public String newToken() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

    public Boolean tokenValido(HttpServletRequest request) throws UnauthorizedException {
        Boolean retorno = false;

        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty()) {
            throw new UnauthorizedException("Token de autorização não informado.");
        }

        Token item = tokensService.getByToken(token);
        retorno = true;

        return retorno;
    }
}
