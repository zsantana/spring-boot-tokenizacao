package br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.xbinario.tokenizacao.config.AutenticacaoProfileConfiguration;

@Component
public class CredencialAcesso {

    private final AutenticacaoProfileConfiguration config;
    
    public CredencialAcesso(AutenticacaoProfileConfiguration config) {
        this.config = config;
    }


    public LoginSystemReqDTO obterCredencialLogin(){

        var credencial = new LoginSystemReqDTO();
        credencial.setCanal(config.getCanal());
        credencial.setTipoUsuario(config.getTipousuario());
        credencial.setIdUsuario(config.getUsuario());
        credencial.setPeriferico(config.getPeriferico());
        credencial.setColId(config.getRequestid());
        credencial.setServer(config.getCloudserver());
        credencial.setEmpresa(config.getEmpresa());
        credencial.setDependencia(config.getDependencia());
        credencial.setIdioma(config.getIdioma());
        credencial.setFluxoDados(config.getFluxodados());
        credencial.setParalelo(config.getParalelo());

        return credencial; 

    }


    public LogoutSystemReqDTO obterCredencialLogout(String ticket, String sessao){

        var credencial = new LogoutSystemReqDTO();
        credencial.setCanal(config.getCanal());
        credencial.setTipoUsuario(config.getTipousuario());
        credencial.setIdUsuario(config.getUsuario());
        credencial.setPeriferico(config.getPeriferico());
        credencial.setColId(config.getRequestid() +  "X" + new Date().getTime());
        credencial.setServer(config.getCloudserver());
        credencial.setEmpresa(config.getEmpresa());
        credencial.setDependencia(config.getDependencia());
        credencial.setIdioma(config.getIdioma());
        credencial.setFluxoDados(config.getFluxodados());
        credencial.setParalelo(config.getParalelo());

        credencial.setTicket(ticket);
        credencial.setSession(sessao);

        return credencial; 

    }
    
}
