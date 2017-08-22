package com.ciandt.includeday6.backend.util;

import com.ciandt.includeday6.backend.entity.Agendamentos;
import com.ciandt.includeday6.backend.entity.Avaliacoes;
import com.ciandt.includeday6.backend.entity.Estabelecimentos;
import com.ciandt.includeday6.backend.entity.LogChamadas;
import com.ciandt.includeday6.backend.entity.TiposDeficiencia;
import com.ciandt.includeday6.backend.entity.Token;
import com.ciandt.includeday6.backend.entity.Usuarios;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class OfyService {

    static {
        ObjectifyService.register(Agendamentos.class);
        ObjectifyService.register(Avaliacoes.class);
        ObjectifyService.register(Estabelecimentos.class);
        ObjectifyService.register(LogChamadas.class);
        ObjectifyService.register(TiposDeficiencia.class);
        ObjectifyService.register(Token.class);
        ObjectifyService.register(Usuarios.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

}