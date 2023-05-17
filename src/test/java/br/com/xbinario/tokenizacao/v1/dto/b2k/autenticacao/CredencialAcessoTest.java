package br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CredencialAcessoTest {

    @Autowired
    private CredencialAcesso credencialAcesso;

    
    @Test
    void testObterCredencialLogin() {

        var loginReqDTO = credencialAcesso.obterCredencialLogin();
        
        assertEquals(loginReqDTO.getCanal(), "1");
        assertEquals(loginReqDTO.getTipoUsuario(), "1");
        assertEquals(loginReqDTO.getIdUsuario(), "1");
        assertEquals(loginReqDTO.getPeriferico(), "1");
        assertEquals(loginReqDTO.getColId(), "1");
        assertEquals(loginReqDTO.getServer(), "1");
        assertEquals(loginReqDTO.getEmpresa(), "1");
        assertEquals(loginReqDTO.getDependencia(), "1");
        assertEquals(loginReqDTO.getIdioma(), "1");
        assertEquals(loginReqDTO.getFluxoDados(), "1");
        assertEquals(loginReqDTO.getParalelo(), "1");

    }

    @Test
    void testObterCredencialLogout() {

        String ticket = "ticket";
        String sessao = "sessao";

        var logoutReqDTO = credencialAcesso.obterCredencialLogout(ticket, sessao);
        assertEquals(logoutReqDTO.getCanal(), "1");
        assertEquals(logoutReqDTO.getTipoUsuario(), "1");
        assertEquals(logoutReqDTO.getIdUsuario(), "1");
        assertEquals(logoutReqDTO.getPeriferico(), "1");
        assertNotNull(logoutReqDTO.getColId());
        assertEquals(logoutReqDTO.getServer(), "1");
        assertEquals(logoutReqDTO.getEmpresa(), "1");
        assertEquals(logoutReqDTO.getDependencia(), "1");
        assertEquals(logoutReqDTO.getIdioma(), "1");
        assertEquals(logoutReqDTO.getFluxoDados(), "1");
        assertEquals(logoutReqDTO.getParalelo(), "1");
        assertEquals(ticket, logoutReqDTO.getTicket(), "ticket");
        assertEquals(sessao, logoutReqDTO.getSession(), "sessao");
        
    }
}
