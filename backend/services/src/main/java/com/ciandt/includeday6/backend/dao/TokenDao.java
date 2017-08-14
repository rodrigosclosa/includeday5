package com.ciandt.includeday6.backend.dao;


import com.ciandt.includeday6.backend.entity.Token;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class TokenDao extends GenericDao<Token> {
    private static TokenDao ourInstance = new TokenDao();
    public static TokenDao getInstance() {
        return ourInstance;
    }

    private TokenDao() {
    }
}