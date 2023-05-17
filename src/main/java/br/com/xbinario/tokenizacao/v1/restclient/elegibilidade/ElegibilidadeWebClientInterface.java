package br.com.xbinario.tokenizacao.v1.restclient.elegibilidade;


import java.util.Optional;

import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.TokenDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.RequestElegibilidadeDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.ResponseELegibilidadeDTO;


public interface ElegibilidadeWebClientInterface {
    
    public Optional<ResponseELegibilidadeDTO> identificarPlataforma(TokenDTO token, RequestElegibilidadeDTO reqDTO);
    public Optional<ResponseELegibilidadeDTO> validacaoBradescard(TokenDTO token, RequestElegibilidadeDTO reqDTO);
    public Optional<ResponseELegibilidadeDTO> registrarElegibilidade(TokenDTO token, RequestElegibilidadeDTO reqDTO);
    
}
