package br.com.xbinario.tokenizacao.v1.restclient.elegibilidade;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import br.com.xbinario.tokenizacao.config.ElegibilidadeProfileConfiguration;
import br.com.xbinario.tokenizacao.infra.webclient.WebClientService;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.TokenDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.RequestElegibilidadeDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.ResponseELegibilidadeDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ElegibilidadeWebClientService implements ElegibilidadeWebClientInterface{

    
    private final ElegibilidadeProfileConfiguration config;
    private final WebClientService webClientService;

    public ElegibilidadeWebClientService(final ElegibilidadeProfileConfiguration config, final WebClientService webClientService) {
        this.config = config;
        this.webClientService = webClientService;
    }


    private HttpHeaders obterHeaders( String sessao, String ticket) {

        var httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Type","application/json");
        httpHeaders.add("Accept", "*/*");

        httpHeaders.add("x-sessao", sessao);
        httpHeaders.add("x-ticket", ticket);
        httpHeaders.add("x-request-id", config.getRequestid());
        

        log.info("Cabeçalho: {}", httpHeaders);
        return httpHeaders;
    }


    @Override
    public Optional<ResponseELegibilidadeDTO> identificarPlataforma(TokenDTO token, RequestElegibilidadeDTO reqDTO) {
        ResponseELegibilidadeDTO response = null;

        log.info("### Requisicao Elegibilidade: {}", reqDTO);
        var h = obterHeaders(token.getData().getSession(), token.getData().getTicket());

        response = webClientService.executePostWithHeaders(reqDTO, 
                                             ResponseELegibilidadeDTO.class, 
                                                           config.getUrl() + config.getEndpoint(),
                                                           h);
    
        log.info("### Resultado identificarPlataforma: {}", response);
        return Optional.of(response);
    }

    @Override
    public Optional<ResponseELegibilidadeDTO> validacaoBradescard(TokenDTO token, RequestElegibilidadeDTO reqDTO) {
        ResponseELegibilidadeDTO response = new ResponseELegibilidadeDTO();
        // TODO ...
        log.info("### Resultado validacaoBradescard: {}", response);
        return Optional.of(response);
    }

    @Override
    public Optional<ResponseELegibilidadeDTO> registrarElegibilidade(TokenDTO token, RequestElegibilidadeDTO reqDTO) {
        var response = new ResponseELegibilidadeDTO();
        // TODO ...
        log.info("### Resultado registrarElegibilidade: {}", response);
        return Optional.of(response);
    }
    

    
}
