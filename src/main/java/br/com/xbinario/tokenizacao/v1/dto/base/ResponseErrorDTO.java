package br.com.xbinario.tokenizacao.v1.dto.base;

import lombok.Data;

@Data
public class ResponseErrorDTO {

    private String codeErro;
    private String fieldValidate;
    private String messageError;
    
}
