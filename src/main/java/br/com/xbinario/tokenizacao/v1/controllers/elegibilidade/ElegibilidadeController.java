package br.com.xbinario.tokenizacao.v1.controllers.elegibilidade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.RequestElegibilidadeDTO;
import br.com.xbinario.tokenizacao.v1.dto.b2k.eligibility.ResponseELegibilidadeDTO;
import br.com.xbinario.tokenizacao.v1.services.elegibilidade.ElegibilidadeService;


@RestController
@RequestMapping(value = "/api/v1/visa-tokenizacao/checkeligibility" )
@Tag(name = "open-api")
@Slf4j
public class ElegibilidadeController {


    @Autowired
    ElegibilidadeService service;


    @Operation(summary = "Verifica a elegibilidade do cartão", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Requisição não encontrada"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar o upload de arquivo"),
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseELegibilidadeDTO> openShift(@Valid @RequestBody RequestElegibilidadeDTO reqDTO) throws Exception {

        log.info("obter consulta Elegibilidade: " + reqDTO.toString());
        var resultadoDTO = service.check(reqDTO);
        return new ResponseEntity<ResponseELegibilidadeDTO>(resultadoDTO, HttpStatus.OK);
        
    }


}
