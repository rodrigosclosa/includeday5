package com.ciandt.includeday6.backend.endpoint;

import com.ciandt.includeday6.backend.business.EstabelecimentosBO;
import com.ciandt.includeday6.backend.dao.EstabelecimentosDao;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 13/12/16.
 */
@Api(
        name = "estabelecimentos",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.backend.includeday6.ciandt.com",
                ownerName = "endpoint.backend.includeday6.ciandt.com",
                packagePath = ""
        )
)
public class EstabelecimentosEndpoint {
    private EstabelecimentosBO estabelecimentosBO;

    public EstabelecimentosEndpoint() {
        estabelecimentosBO = new EstabelecimentosBO();
    }

    @ApiMethod(name = "list", path = "", httpMethod = ApiMethod.HttpMethod.GET)
    public void list(HttpServletRequest req, @Named("nome") String nome) throws UnauthorizedException {
        estabelecimentosBO.listAll(req, nome);
    }

}
