package br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility;

import lombok.Data;

@Data
public class ResponseELegibilidadeDTO{ 
    private String errorCode;
    private CardMetadataInfo cardMetadataInfo;
}
