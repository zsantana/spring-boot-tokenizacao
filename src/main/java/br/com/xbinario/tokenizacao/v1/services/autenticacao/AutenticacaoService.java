package br.com.xbinario.tokenizacao.v1.services.autenticacao;



import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.xbinario.tokenizacao.config.AutenticacaoProfileConfiguration;
import br.com.xbinario.tokenizacao.infra.webclient.WebClientService;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.CredencialAcesso;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.CredencialAcessoInterface;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.TokenDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.TokenData;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AutenticacaoService {

    
    private final AutenticacaoProfileConfiguration config;
    private final WebClientService webClientService;
    

    public AutenticacaoService(final AutenticacaoProfileConfiguration config, final WebClientService webClientService) {
        this.config = config;
        this.webClientService = webClientService;
    }


    public TokenDTO login(final CredencialAcesso credencialAcesso) {

        var requestDTO = credencialAcesso.obterCredencialLogin();
        return post(requestDTO, config.getUrl() + config.getEndPointlogin());

    }


    public void logout(final TokenDTO token, final CredencialAcesso credencialAcesso) {

        var requestDTO = credencialAcesso.obterCredencialLogout(token.data.getTicket(), token.data.getSession());
        post(requestDTO, config.getUrl() + config.getEndPointlogout());

    }


    public TokenDTO mock() {

        var tokenData = new TokenData();
        tokenData.setSession(UUID.randomUUID().toString());
        tokenData.setTicket(UUID.randomUUID().toString());

        var response = new TokenDTO();
        response.setData(tokenData);

        return response;

    }
    

    private TokenDTO post(final CredencialAcessoInterface reqDTO, final String uri) {

        var response = webClientService.executePost(reqDTO, TokenDTO.class, uri);
        log.info("resultado: {} ", response);                  
        
        return response;
    }
    
}
