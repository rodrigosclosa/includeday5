package com.ciandt.includeday6.backend.endpoint;

import com.ciandt.includeday6.backend.business.TiposDeficienciaBO;
import com.ciandt.includeday6.backend.business.UsuariosBO;
import com.ciandt.includeday6.backend.entity.Usuarios;
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
        name = "usuarios",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoint.backend.includeday6.ciandt.com",
                ownerName = "endpoint.backend.includeday6.ciandt.com",
                packagePath = ""
        )
)
public class UsuariosEndpoint {
    private UsuariosBO usuariosBO;

    public UsuariosEndpoint() {
        usuariosBO = new UsuariosBO();
    }

    @ApiMethod(name = "login", path = "login", httpMethod = ApiMethod.HttpMethod.POST)
    public void login(HttpServletRequest req, Usuarios usuario) throws UnauthorizedException, ConflictException {
        usuariosBO.login(req, usuario);
    }

    @ApiMethod(name = "create", path = "create", httpMethod = ApiMethod.HttpMethod.POST)
    public Usuarios create(HttpServletRequest req, Usuarios usuario) throws UnauthorizedException, ConflictException {
        return usuariosBO.create(req, usuario);
    }
}
