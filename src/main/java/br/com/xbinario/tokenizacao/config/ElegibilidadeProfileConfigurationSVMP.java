package br.com.xbinario.tokenizacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ElegibilidadeProfileConfigurationSVMP {
    
    @Value("${api-autenticacao-b2k-url}")
    private String url;

    @Value("${api-flow-mainframe}")
    private String endpointFlow;

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
    String dependencia;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.idioma}")
    String idioma;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.fluxodados}")
    String fluxodados;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.paralelo}")
    String paralelo;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.periferico}")
    String periferico;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.requestid}")
    String requestid;

    @Value("${api.autenticacao-cws.tokenizacao-visa.header.cloudserver}")
    String cloudserver;

}