package br.com.xbinario.tokenizacao.v1.services.elegibilidade;


import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.CredencialAcesso;
import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.RequestElegibilidadeDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.ResponseELegibilidadeDTO;
import br.com.xbinario.tokenizacao.exceptions.ElegibilidadeException;
import br.com.xbinario.tokenizacao.v1.restclient.elegibilidade.ElegibilidadeWebClientInterface;
import br.com.xbinario.tokenizacao.v1.services.autenticacao.AutenticacaoService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class ElegibilidadeService {
    

    private ElegibilidadeWebClientInterface service;
    private AutenticacaoService autenticacaoService;
    private CredencialAcesso credencialAcessoLogin;


    public ElegibilidadeService(ElegibilidadeWebClientInterface service, 
                                AutenticacaoService autenticacaoService,
                                CredencialAcesso credencialAcessoLogin) {
        this.service = service;
        this.autenticacaoService = autenticacaoService;
        this.credencialAcessoLogin = credencialAcessoLogin;
    }



    public ResponseELegibilidadeDTO check(RequestElegibilidadeDTO reqDTO) {

        Optional<ResponseELegibilidadeDTO> ret01 = null;

        log.info("Efetuando login sessão mainframe");
        var token = autenticacaoService.login(credencialAcessoLogin);

        try  {

            ret01 = service.identificarPlataforma(token, reqDTO);
            
            if (ret01.isPresent()){

            }else{
                throw new ElegibilidadeException("422", "Erro de validação (identificarPlataforma)");
            }


        } finally  {

            autenticacaoService.logout(token, credencialAcessoLogin);
            log.info("Efetuando logout sessão mainframe");

        }

        return ret01.get();
    }

}
