package br.com.xbinario.tokenizacao.exceptions;


public class ElegibilidadeException extends RuntimeException {

    private String codigoErro; 

    public ElegibilidadeException() {
    }

    public ElegibilidadeException(String message) {
        super(message);
    }

    public ElegibilidadeException(Throwable cause) {
        super(cause);
    }

    public ElegibilidadeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElegibilidadeException(String codigoErro, String message) {
        super(message);
        this.codigoErro = codigoErro;
    }

    public ElegibilidadeException(String codigoErro, String message, Throwable cause) {
        super(message, cause);
        this.codigoErro = codigoErro;
    }

    public ElegibilidadeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    public void setCodigoErro(String codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getCodigoErro() {
        return codigoErro;
    }

}
