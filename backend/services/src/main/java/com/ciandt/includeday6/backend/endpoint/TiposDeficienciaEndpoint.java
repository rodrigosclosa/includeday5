package com.ciandt.includeday6.backend.endpoint;

import com.ciandt.includeday6.backend.business.EstabelecimentosBO;
import com.ciandt.includeday6.backend.business.TiposDeficienciaBO;
import com.ciandt.includeday6.backend.entity.TiposDeficiencia;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 13/12/16.
 */
@Api(
        name = "tiposdeficiencia",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.backend.includeday6.ciandt.com",
                ownerName = "endpoint.backend.includeday6.ciandt.com",
                packagePath = ""
        )
)
public class TiposDeficienciaEndpoint {
    private TiposDeficienciaBO tiposDeficienciaBO;

    public TiposDeficienciaEndpoint() {
        tiposDeficienciaBO = new TiposDeficienciaBO();
    }

    @ApiMethod(name = "list", path = "", httpMethod = ApiMethod.HttpMethod.GET)
    public List<TiposDeficiencia> list(HttpServletRequest req) throws UnauthorizedException {
        return tiposDeficienciaBO.listAll(req);
    }

}
