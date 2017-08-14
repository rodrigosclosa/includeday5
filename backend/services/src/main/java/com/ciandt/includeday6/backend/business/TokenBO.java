package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.TokenDao;
import com.ciandt.includeday6.backend.entity.Token;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import java.util.List;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class TokenBO {
    public TokenBO() {
    }

    public List<Token> list() {
        return TokenDao.getInstance().listAll();
    }

    public List<Token> list(String codigo) throws NotFoundException {

        List<Token> list = TokenDao.getInstance().listByProperty("token", codigo);

        if(list == null || list.size() < 1) {
            throw new NotFoundException(String.format("Não foram encontrados dados para o código: %s", codigo));
        }

        return list;
    }

    public Token getByCodigo(String codigo) throws NotFoundException {
        Token item = TokenDao.getInstance().getByProperty("codigo", codigo);

        if(item == null) {
            throw new NotFoundException("Token não encontrado.");
        }

        return item;
    }

    public Token getByToken(String token) throws UnauthorizedException {
        Query.Filter f1 = new Query.FilterPredicate("token", Query.FilterOperator.EQUAL, token);
        Query.Filter f2 = new Query.FilterPredicate("ativo", Query.FilterOperator.EQUAL, true);
        Query.Filter filter = Query.CompositeFilterOperator.and(f1, f2);

        Token item = TokenDao.getInstance().getByFilter(filter);

        if(item == null) {
            throw new UnauthorizedException("Acesso negado. Token não encontrado ou inválido.");
        }

        return item;
    }

    public Token insert(Token item) throws ConflictException, NotFoundException {
        if(item == null)
        {
            throw new ConflictException("Item não informado.");
        }
        else if(item.getCodigo() == null)
        {
            throw new ConflictException("Código não informado.");
        }
        else if(item.getDescricao() == null)
        {
            throw new ConflictException("Descrição não informada.");
        }
        else if(item.getToken() == null)
        {
            throw new ConflictException("Token não informado.");
        }
        else if(item.getAtivo() == null)
        {
            throw new ConflictException("Ativo não informado.");
        }

        TokenDao.getInstance().insert(item);

        return item;
    }

    public Token update(Token item) throws ConflictException, NotFoundException {
        if(item == null)
        {
            throw new ConflictException("Item não informado.");
        }
        else if(item.getCodigo() == null)
        {
            throw new ConflictException("Código não informado.");
        }
        else if(item.getDescricao() == null)
        {
            throw new ConflictException("Descrição não informada.");
        }
        else if(item.getToken() == null)
        {
            throw new ConflictException("Token não informado.");
        }
        else if(item.getAtivo() == null)
        {
            throw new ConflictException("Ativo não informado.");
        }

        Token u = TokenDao.getInstance().getById(item.getId());

        if(u == null) {
            throw new NotFoundException("Token não encontrado");
        }

        TokenDao.getInstance().update(item);

        return item;
    }

    public void remove(long id) throws ConflictException, NotFoundException {
        Token item = TokenDao.getInstance().getByKey(id);

        if (item == null) {
            throw new NotFoundException("Token não encontrado.");
        }

        TokenDao.getInstance().delete(item);
    }
}
