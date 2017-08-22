package com.ciandt.includeday6.backend.endpoint;

import com.ciandt.includeday6.backend.business.AgendamentosBO;
import com.ciandt.includeday6.backend.business.AvaliacaoBO;
import com.ciandt.includeday6.backend.entity.Agendamentos;
import com.ciandt.includeday6.backend.entity.Avaliacoes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 13/12/16.
 */
@Api(
        name = "avaliacoes",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.backend.includeday6.ciandt.com",
                ownerName = "endpoint.backend.includeday6.ciandt.com",
                packagePath = ""
        )
)
public class AvaliacoesEndpoint {
    private AvaliacaoBO avaliacaoBO;

    public AvaliacoesEndpoint() {
        avaliacaoBO = new AvaliacaoBO();
    }

    @ApiMethod(name = "create", path = "create", httpMethod = ApiMethod.HttpMethod.POST)
    public void create(HttpServletRequest req, Avaliacoes avaliacoes) throws UnauthorizedException, ConflictException {
        avaliacaoBO.create(req, avaliacoes);
    }

}
