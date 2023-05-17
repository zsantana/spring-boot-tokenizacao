package br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class LogoutSystemReqDTO extends LoginSystemReqDTO {

    private String ticket;
    private String session;

}
