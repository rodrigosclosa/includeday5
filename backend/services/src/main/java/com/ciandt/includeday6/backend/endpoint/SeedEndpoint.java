package com.ciandt.includeday6.backend.endpoint;

import com.ciandt.includeday6.backend.business.SeedDataBO;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 13/12/16.
 */
@Api(
        name = "seed",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.backend.includeday6.ciandt.com",
                ownerName = "endpoint.backend.includeday6.ciandt.com",
                packagePath = ""
        )
)
public class SeedEndpoint {

    private SeedDataBO seedDataBO;

    public SeedEndpoint() {
        seedDataBO = new SeedDataBO();
    }

    @ApiMethod(name = "seedBaseData", path = "seed", httpMethod = ApiMethod.HttpMethod.POST)
    public void seedBaseData(HttpServletRequest req) throws UnauthorizedException {
        seedDataBO.seedBaseData(req);
    }

}
