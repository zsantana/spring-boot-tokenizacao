package br.com.xbinario.tokenizacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class AutenticacaoProfileConfiguration {
    
    @Value("${api-autenticacao-b2k-url}")
    private String url;

    @Value("${api-login-mainframe}")
    private String endPointlogin;

    @Value("${api-logout-mainframe}")
    private String endPointlogout;

    

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.canal}")
    private String canal;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.sessao}")
    private String sessao;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.ticket}")
    private String ticket;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.usuario}")
    private String usuario;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.tipousuario}")
    private String tipousuario;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.empresa}")
    private String empresa;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.dependencia}")
    private String dependencia;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.idioma}")
    private String idioma;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.fluxodados}")
    private String fluxodados;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.paralelo}")
    private String paralelo;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.periferico}")
    private String periferico;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.requestid}")
    private String requestid;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.cloudserver}")
    private String cloudserver;

}