package br.com.xbinario.tokenizacao.v1.dto.base;


import lombok.Data;

@Data
public class BaseRequestDTO {
   
    private String tokenRequestorID;
    private String tokenReferenceID;

}

