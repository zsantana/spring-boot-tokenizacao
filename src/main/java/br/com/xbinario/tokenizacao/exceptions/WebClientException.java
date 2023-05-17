package br.com.xbinario.tokenizacao.exceptions;

import org.springframework.http.HttpStatus;

public class WebClientException extends RuntimeException {

    private HttpStatus codigoErro; 

    public WebClientException() {
    }

    public WebClientException(HttpStatus codigoErro, String message) {
        super(message);
        this.codigoErro = codigoErro;
    }

    public WebClientException(HttpStatus codigoErro, String message, Throwable cause) {
        super(message, cause);
        this.codigoErro = codigoErro;
    }

    public WebClientException( String message) {
        super(message);
    }

    public WebClientException(Throwable cause) {
        super(cause);
    }

    public WebClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getCodigoErro() {
        return codigoErro;
    }

}
