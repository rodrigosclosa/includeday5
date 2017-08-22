package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.UsuariosDao;
import com.ciandt.includeday6.backend.entity.LogChamadas;
import com.ciandt.includeday6.backend.entity.Usuarios;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigogs on 22/08/17.
 */

public class UsuariosBO {
    private UsuariosDao usuariosDao;

    public UsuariosBO() {
        usuariosDao = new UsuariosDao();
    }

    public void login(HttpServletRequest req, Usuarios usuario) throws UnauthorizedException, ConflictException {
        LogBO.getInstance().log(req, "Usuarios", "login");

        if(usuario == null){
            throw new ConflictException("Email e senha não informados.");
        }

        Query.Filter f1 = new Query.FilterPredicate("email", Query.FilterOperator.EQUAL, usuario.getEmail());
        Query.Filter f2 = new Query.FilterPredicate("senha", Query.FilterOperator.EQUAL, usuario.getSenha());
        Query.Filter filter = Query.CompositeFilterOperator.and(f1, f2);

        Usuarios usuarios = usuariosDao.getByFilter(filter);

        if(usuarios == null) {
            throw new UnauthorizedException("Usuário não encontrado.");
        }
    }

    public Usuarios create(HttpServletRequest req, Usuarios usuario) throws ConflictException, UnauthorizedException {
        LogBO.getInstance().log(req, "Usuarios", "create");

        if(usuario == null) {
            throw new ConflictException("Dados do usuário não informados.");
        }

        Usuarios user = usuariosDao.getByProperty("email", usuario.getEmail());

        if(user != null) {
            user.setEmail(usuario.getEmail());
            user.setNome(usuario.getNome());
            user.setSenha(usuario.getSenha());

            usuario = usuariosDao.update(user);
        } else {
            usuario = usuariosDao.insert(usuario);
        }

        return usuario;
    }
}
