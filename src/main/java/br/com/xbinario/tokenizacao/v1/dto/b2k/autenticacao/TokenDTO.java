package br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TokenDTO {
    
    @JsonProperty("data")
    public TokenData data;
    
}
