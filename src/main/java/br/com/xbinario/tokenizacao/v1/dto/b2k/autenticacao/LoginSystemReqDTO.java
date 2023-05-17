package br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class LoginSystemReqDTO implements CredencialAcessoInterface{

    @JsonProperty("canal")
    private String canal;

    @JsonProperty("tipoUsuario")
    private String tipoUsuario;

    @JsonProperty("idUsuario")
    private String idUsuario;

    @JsonProperty("periferico")
    private String periferico;

    @JsonProperty("colId")
    private String colId;

    @JsonProperty("server")
    private String server;

    @JsonProperty("empresa")
    private String empresa;

    @JsonProperty("dependencia")
    private String dependencia;

    @JsonProperty("idioma")
    private String idioma;

    @JsonProperty("fluxoDados")
    private String fluxoDados;

    @JsonProperty("paralelo")
    private String paralelo;

}
