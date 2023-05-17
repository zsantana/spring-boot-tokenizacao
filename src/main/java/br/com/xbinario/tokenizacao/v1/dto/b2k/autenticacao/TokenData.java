package br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenData {

    @JsonProperty("ticket")
    private String ticket;

    @JsonProperty("session")
    private String session;
}
