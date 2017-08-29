package com.ciandt.includeday6.backend.business;

import com.ciandt.includeday6.backend.dao.EstabelecimentosDao;
import com.ciandt.includeday6.backend.dao.TiposDeficienciaDao;
import com.ciandt.includeday6.backend.dao.TokenDao;
import com.ciandt.includeday6.backend.entity.Estabelecimentos;
import com.ciandt.includeday6.backend.entity.TiposDeficiencia;
import com.ciandt.includeday6.backend.entity.Token;
import com.ciandt.includeday6.backend.helper.TokenHelper;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rodrigosclosa on 28/12/16.
 */

public class SeedDataBO {
    private EstabelecimentosDao estabelecimentosDao;
    private TiposDeficienciaDao tiposDeficienciaDao;

    public SeedDataBO() {
        estabelecimentosDao = new EstabelecimentosDao();
        tiposDeficienciaDao = new TiposDeficienciaDao();
    }

    public void seedBaseData(HttpServletRequest request) throws UnauthorizedException {

        //Tokens de Acesso
        Token token = TokenDao.getInstance().getByProperty("codigo", "FRONTEND");

        if (token == null) {
            token = new Token();
            token.setDescricao("Frontend da Aplicação");
            token.setCodigo("FRONTEND");
            token.setToken("0ABCCC629E2C4DAAB2DE36A042E3BB74");
            token.setAtivo(true);

            TokenDao.getInstance().insert(token);
        }

        token = TokenDao.getInstance().getByProperty("codigo", "POSTMAN");

        if (token == null) {
            token = new Token();
            token.setDescricao("Postman Testes");
            token.setCodigo("POSTMAN");
            token.setToken("1DD0B7C1D6B9415EBC783CA432F60682");
            token.setAtivo(true);

            TokenDao.getInstance().insert(token);
        }

        List<Estabelecimentos> estabelecimentosList = new ArrayList<Estabelecimentos>();

        estabelecimentosList.add(new Estabelecimentos("Shopping Dom Pedro", "Shopping legal na cidade de Campinas", "https://www2.portalnovidade.com.br/wp-content/uploads/2016/04/dpedro.jpg", "Campinas", "SP", "Paulo de Andrade"));
        estabelecimentosList.add(new Estabelecimentos("Shopping Iguatemi", "Lindo shopping na cidade de Campinas", "https://i.ytimg.com/vi/JvBtpDwaWLk/maxresdefault.jpg", "Campinas", "SP", "Jose Paulo"));
        estabelecimentosList.add(new Estabelecimentos("Lagoa do Taquaral", "Parque grande na cidade de Campinas", "https://corroporcorrer.files.wordpress.com/2010/03/dsc01787-caravela2.jpg", "Campinas", "SP", "João Garcia"));
        estabelecimentosList.add(new Estabelecimentos("Lojas Renner", "Loja de roupas e acessórios", "https://jovemaprendiz2017.pro.br/wp-content/uploads/2016/01/3-5.jpg", "Campinas", "SP", "Manoel Sales"));
        estabelecimentosList.add(new Estabelecimentos("Museu da Imagem e do Som", "Museu da imagem e som na cidade de Campinas", "http://jornalocal.com.br/site/wp-content/uploads/2014/07/mis-campinas.jpg", "Campinas", "SP", "Daniel Gomes"));
        estabelecimentosList.add(new Estabelecimentos("Museu de História Natural", "Museu de história natural na cidade de Campinas", "https://upload.wikimedia.org/wikipedia/commons/6/6b/Museu_de_Hist%C3%B3ria_Natural_de_Campinas.JPG", "Campinas", "SP", "Gabriel Santos"));
        estabelecimentosList.add(new Estabelecimentos("Museu Carlos Gomes", "Museu Carlos Gomes na cidade de Campinas", "https://www.guiadasemana.com.br/contentFiles/system/pictures/2013/6/80815/original/254873-170214579704862-4105890-n.jpg", "Campinas", "SP", "Paula Pedroso"));
        estabelecimentosList.add(new Estabelecimentos("Shopping Galleria", "Shopping bacana na cidade de Campinas", "http://static.panoramio.com/photos/large/77856815.jpg", "Campinas", "SP", "Cristina Silva"));
        estabelecimentosList.add(new Estabelecimentos("Bosque dos Jequitibás", "Lindo bosque na cidade de Campinas", "https://imagesapt.apontador-assets.com/fit-in/640x480/943f90c46665430db599cb01a465dbaa/bosque-dos-jequitibas.jpg", "Campinas", "SP", "Mariana Gonçalves"));
        estabelecimentosList.add(new Estabelecimentos("Livraria Saraiva", "Mega livraria na cidade de Campinas", "https://www.epochtimes.com.br/wp-content/uploads/2014/08/br-saraiva-amazon-aquisicao.jpg", "Campinas", "SP", "Vitória Camilo"));

        for (Estabelecimentos estabelecimento : estabelecimentosList) {
            if(estabelecimentosDao.getByProperty("nome", estabelecimento.getNome()) == null) {
                estabelecimentosDao.insert(estabelecimento);
            }
        }

        List<TiposDeficiencia> tiposDeficienciaList = new ArrayList<TiposDeficiencia>();

        tiposDeficienciaList.add(new TiposDeficiencia("Visual"));
        tiposDeficienciaList.add(new TiposDeficiencia("Auditiva"));
        tiposDeficienciaList.add(new TiposDeficiencia("Motora"));
        tiposDeficienciaList.add(new TiposDeficiencia("Intelectual"));

        for (TiposDeficiencia tipo : tiposDeficienciaList) {
            if(tiposDeficienciaDao.getByProperty("descricao", tipo.getDescricao()) == null) {
                tiposDeficienciaDao.insert(tipo);
            }
        }

    }
}
