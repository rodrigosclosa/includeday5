package com.ciandt.includeday6.backend.endpoint;

import com.ciandt.includeday6.backend.business.AgendamentosBO;
import com.ciandt.includeday6.backend.business.EstabelecimentosBO;
import com.ciandt.includeday6.backend.entity.Agendamentos;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 13/12/16.
 */
@Api(
        name = "agendamentos",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.backend.includeday6.ciandt.com",
                ownerName = "endpoint.backend.includeday6.ciandt.com",
                packagePath = ""
        )
)
public class AgendamentosEndpoint {
    private AgendamentosBO agendamentosBO;

    public AgendamentosEndpoint() {
        agendamentosBO = new AgendamentosBO();
    }

    @ApiMethod(name = "create", path = "create", httpMethod = ApiMethod.HttpMethod.POST)
    public void create(HttpServletRequest req, Agendamentos agendamentos) throws UnauthorizedException, ConflictException {
        agendamentosBO.create(req, agendamentos);
    }

}
