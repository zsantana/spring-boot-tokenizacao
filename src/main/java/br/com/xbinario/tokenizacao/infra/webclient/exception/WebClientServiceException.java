package br.com.xbinario.tokenizacao.infra.webclient.exception;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.xbinario.tokenizacao.exceptions.WebClientException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebClientServiceException extends RuntimeException  {

    private static final Map<HttpStatus, String> STATUS_MESSAGES = Map.of(
        HttpStatus.BAD_REQUEST, "Erro de requisição",
        HttpStatus.UNAUTHORIZED, "Não autorizado",
        HttpStatus.FORBIDDEN, "Proibido",
        HttpStatus.NOT_FOUND, "Recurso não encontrado",
        HttpStatus.METHOD_NOT_ALLOWED, "Método HTTP não permitido",
        HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo de mídia não suportado",
        HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor",
        HttpStatus.SERVICE_UNAVAILABLE, "Serviço indisponível"
    );

    public WebClientServiceException(Exception ex) {

        if (ex instanceof WebClientResponseException responseEx) {
            
            var statusCode = responseEx.getStatusCode();
            var message = STATUS_MESSAGES.getOrDefault(statusCode, "Erro inesperado");
            var responseBody = responseEx.getResponseBodyAsString();

            log.error(message + ": " + responseEx.getMessage() + " - " + responseBody);
            throw new WebClientException(statusCode, message + ": " + responseBody);

        } else if (ex instanceof WebClientRequestException responseEx) {

            var cause = responseEx.getCause();

            if (cause instanceof UnknownHostException hostException) {

                var message = hostException.getMessage();
                log.error("URL informada não responde: {}", message);
                throw new WebClientException(HttpStatus.BAD_REQUEST, message);

            } if (cause instanceof ConnectException ConnectException) {

                var message = ConnectException.getMessage();
                log.error("Falha na conexão com servidor: {}", message);
                throw new WebClientException(HttpStatus.INTERNAL_SERVER_ERROR, message);

            }
            else {
                log.error("Erro não mapeado na requisição de autenticação: {}", ex.getMessage());
                throw new WebClientException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
            }

        } else {
            log.error("Erro não mapeado no processo de autenticação do mainframe: {}", ex.getMessage());
            throw new WebClientException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

    }
    
}
