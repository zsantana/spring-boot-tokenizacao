package br.com.xbinario.tokenizacao.exceptions;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "VISA_TOKENIZACAO_GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

    @Value("${server.error.include-exception}")
    private boolean printStackTrace;

    @Value("${server.error.include-exception-log}")
    private boolean printStackTraceLog;


    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<Object> handleAutenticacaoException(WebClientException autenticacaoException,
                                                              WebRequest request) {

        if (this.printStackTraceLog)
            log.error("Falha no processo de tokenização no Mainframe", autenticacaoException);
        else{
            log.error("Falha no processo de tokenização no Mainframe", autenticacaoException.getMessage());
        }    
        
        return buildErrorResponse(
                autenticacaoException,
                autenticacaoException.getCodigoErro(),
                request);
    }

    
    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse( HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Erro na validação de campos obrigatórios.");

        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            log.error("Erro na validação de campos", fieldError.getField() + " : " + fieldError.getDefaultMessage());
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        if (this.printStackTraceLog)
            log.error("Erro na validação de campos", errorResponse);

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(
            Exception exception,
            WebRequest request) {
        
        final String errorMessage = "Erro não mapeado";
        log.error(errorMessage, exception);
        
        return buildErrorResponse(
                exception,
                errorMessage,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
    

    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request) {

        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);

    }


    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);

        if (this.printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }


}
