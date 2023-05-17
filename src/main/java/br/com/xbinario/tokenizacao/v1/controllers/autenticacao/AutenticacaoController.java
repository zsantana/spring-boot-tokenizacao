package br.com.xbinario.tokenizacao.v1.controllers.autenticacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.CredencialAcesso;
import br.com.xbinario.tokenizacao.v1.dto.b2k.autenticacao.TokenDTO;
import br.com.xbinario.tokenizacao.v1.services.autenticacao.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/visa-tokenizacao/autenticacao")
@Tag(name = "open-api")
@Slf4j
public class AutenticacaoController {

    @Autowired
    AutenticacaoService service;

    @Autowired
    CredencialAcesso credencialAcesso;

    private TokenDTO token;
    

    @Operation(summary = "Verifica a elegibilidade do cartão", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Requisição não encontrada"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(value = "/login",  produces = "application/json")
	public Mono<ResponseEntity<TokenDTO>> login(@RequestHeader HttpHeaders headers) {

        this.token = service.login(credencialAcesso);
        log.info("Obtendo token mainframe: " + token.toString());

        return Mono.just(ResponseEntity.ok(token));

    }


    @PostMapping(value = "/logout")
	public Mono<ResponseEntity<String>> logout(@RequestHeader HttpHeaders headers) {

        service.logout(this.token, credencialAcesso);
        log.info("Logout efetuado com sucesso");

        return Mono.just(ResponseEntity.ok("Logout efetuado com sucesso"));

    }


    @GetMapping(value = "/mock-reactive")
	public Mono<ResponseEntity<TokenDTO>> mockReactive() {

        return Mono.just(ResponseEntity.ok(service.mock()));

    }


    @GetMapping(value = "/mock")
	public ResponseEntity<TokenDTO> mock() {

        return ResponseEntity.ok(service.mock());

    }
}
