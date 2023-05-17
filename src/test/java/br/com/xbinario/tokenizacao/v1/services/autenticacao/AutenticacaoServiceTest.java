package br.com.xbinario.tokenizacao.v1.services.autenticacao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import br.com.xbinario.tokenizacao.config.AutenticacaoProfileConfiguration;
import br.com.xbinario.tokenizacao.infra.webclient.WebClientService;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.CredencialAcesso;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.LoginSystemReqDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.LogoutSystemReqDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.TokenData;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.TokenDTO;

@SpringBootTest
public class AutenticacaoServiceTest {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @MockBean
    private WebClientService webClientService;

    @Autowired
    CredencialAcesso credencialAcesso;

    @MockBean
    private AutenticacaoProfileConfiguration config;


    @Test
    void testLogin() {

        // Arrange
        String url = "http://bradesco.com";
        String endPointLogin = "/login";
        
        var expectedTokenDTO  = new TokenData();
        expectedTokenDTO.setSession("xxx");
        expectedTokenDTO.setTicket("xxx");

        var token = new TokenDTO();
        token.setData(expectedTokenDTO);

        String uri = url + endPointLogin;
        given(config.getUrl()).willReturn(url);
        given(config.getEndPointlogin()).willReturn(endPointLogin);

        given(webClientService.executePost(any(LoginSystemReqDTO.class), eq(TokenDTO.class), eq(uri)))
                .willReturn(token);

        // Act
        var actualTokenDTO = autenticacaoService.login(credencialAcesso);

        // Assert
        assertEquals(token, actualTokenDTO);
        verify(webClientService).executePost(any(LoginSystemReqDTO.class), eq(TokenDTO.class), eq(uri));
    

    }

    @Test
    void testLogout() {

        // Arrange
        String url = "http://bradesco.com";
        String endPointLogout = "/logout";
        
        var expectedTokenDTO  = new TokenData();
        expectedTokenDTO.setSession("xxx");
        expectedTokenDTO.setTicket("xxx");

        var token = new TokenDTO();
        token.setData(expectedTokenDTO);


        String uri = url + endPointLogout;

        given(config.getUrl()).willReturn(url);
        given(config.getEndPointlogout()).willReturn(endPointLogout);

        // Act
        autenticacaoService.logout(token, credencialAcesso);

        // Assert
        verify(webClientService).executePost(any(LogoutSystemReqDTO.class), eq(TokenDTO.class), eq(uri));

    }
}
